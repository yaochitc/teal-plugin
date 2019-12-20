package io.yaochi.intellij.plugin.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.patterns.PatternCondition;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import io.yaochi.intellij.plugin.psi.TEALFile;
import io.yaochi.intellij.plugin.psi.TEALTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static io.yaochi.intellij.plugin.completion.TEALCompletionUtil.KEYWORD_PRIORITY;

public class TEALKeywordCompletionContributor extends CompletionContributor {
	public TEALKeywordCompletionContributor() {
		extend(CompletionType.BASIC, topLevelPattern(), new TEALKeywordCompletionProvider(KEYWORD_PRIORITY,
				AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE, "int", "byte", "arg", "txn", "global", "gtxn", "load",
				"store", "err", "bnz", "pop", "dup"));
		extend(CompletionType.BASIC, txnFieldPattern(), new TEALKeywordCompletionProvider(KEYWORD_PRIORITY,
				AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE, "Sender", "Fee", "FirstValid", "FirstValidTime", "LastValid", "Note", "Lease", "Receiver", "Amount"
				, "CloseRemainderTo", "VotePK", "SelectionPK", "VoteFirst", "VoteLast", "VoteKeyDilution", "Type", "TypeEnum", "XferAsset"
				, "AssetAmount", "AssetSender", "AssetReceiver", "AssetCloseTo", "GroupIndex", "TxID"));
		extend(CompletionType.BASIC, globalFieldPattern(), new TEALKeywordCompletionProvider(KEYWORD_PRIORITY,
				AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE, "MinTxnFee", "MinBalance", "MaxTxnLife", "ZeroAddress", "GroupSize"));

	}

	private static PsiElementPattern.Capture<PsiElement> topLevelPattern() {
		return onStatementBeginning(TEALTypes.IDENTIFIER).withParent(psiElement(TEALFile.class));
	}

	private static PsiElementPattern.Capture<PsiElement> txnFieldPattern() {
		return psiElement(TEALTypes.IDENTIFIER).withParent(psiElement(TEALFile.class))
				.afterLeaf("txn");
	}

	private static PsiElementPattern.Capture<PsiElement> globalFieldPattern() {
		return psiElement(TEALTypes.IDENTIFIER).withParent(psiElement(TEALFile.class))
				.afterLeaf("global");
	}

	private static PsiElementPattern.Capture<PsiElement> onStatementBeginning(@NotNull IElementType... tokenTypes) {
		return psiElement().withElementType(TokenSet.create(tokenTypes)).with(new OnStatementBeginning());
	}


	private static class OnStatementBeginning extends PatternCondition<PsiElement> {
		OnStatementBeginning() {
			super("on statement beginning");
		}

		@Override
		public boolean accepts(@NotNull PsiElement psiElement, ProcessingContext context) {
			return onStatementBeginning(psiElement);
		}
	}

	private static PsiElement prevVisibleLeafOrNewLine(PsiElement element) {
		PsiElement prevLeaf = element;
		while ((prevLeaf = PsiTreeUtil.prevLeaf(prevLeaf)) != null) {
			if (prevLeaf instanceof PsiComment || prevLeaf instanceof PsiErrorElement) {
				continue;
			}
			if (prevLeaf instanceof PsiWhiteSpace) {
				if (prevLeaf.textContains('\n')) {
					return prevLeaf;
				}
				continue;
			}
			break;
		}
		return prevLeaf;
	}

	private static boolean onStatementBeginning(PsiElement psiElement) {
		PsiElement prevLeaf = prevVisibleLeafOrNewLine(psiElement);
		if (prevLeaf == null) {
			return true;
		}
		if (prevLeaf instanceof PsiWhiteSpace) {
			return true;
		}
		IElementType type = prevLeaf.getNode().getElementType();
		return type == TEALTypes.SEMICOLON_SYNTHETIC;
	}
}