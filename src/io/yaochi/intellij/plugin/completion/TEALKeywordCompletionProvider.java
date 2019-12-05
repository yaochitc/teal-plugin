package io.yaochi.intellij.plugin.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.impl.TemplateSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TEALKeywordCompletionProvider extends CompletionProvider<CompletionParameters> {
	public static final InsertHandler<LookupElement> EMPTY_INSERT_HANDLER = (context, element) -> {

	};

	private final int myPriority;

	@Nullable
	private final InsertHandler<LookupElement> myInsertHandler;

	@Nullable
	private final AutoCompletionPolicy myCompletionPolicy;

	@NotNull
	private final String[] myKeywords;

	public TEALKeywordCompletionProvider(int priority, String... keywords) {
		this(priority, null, null, keywords);
	}

	public TEALKeywordCompletionProvider(int priority, @Nullable AutoCompletionPolicy completionPolicy, @NotNull String... keywords) {
		this(priority, null, completionPolicy, keywords);
	}

	public TEALKeywordCompletionProvider(int priority, @Nullable InsertHandler<LookupElement> insertHandler, @NotNull String... keywords) {
		this(priority, insertHandler, null, keywords);
	}

	public TEALKeywordCompletionProvider(int myPriority,
										 @Nullable InsertHandler<LookupElement> myInsertHandler,
										 @Nullable AutoCompletionPolicy myCompletionPolicy,
										 @NotNull String[] myKeywords) {
		this.myPriority = myPriority;
		this.myInsertHandler = myInsertHandler;
		this.myCompletionPolicy = myCompletionPolicy;
		this.myKeywords = myKeywords;
	}

	@Override
	protected void addCompletions(@NotNull CompletionParameters completionParameters,
								  @NotNull ProcessingContext processingContext,
								  @NotNull CompletionResultSet result) {
		for (String keyword : myKeywords) {
			result.addElement(createKeywordLookupElement(keyword));
		}
	}

	@NotNull
	private LookupElement createKeywordLookupElement(@NotNull String keyword) {
		InsertHandler<LookupElement> insertHandler = ObjectUtils.chooseNotNull(myInsertHandler,
				createTemplateBasedInsertHandler("teal_" + keyword));
		LookupElement result = createKeywordLookupElement(keyword, myPriority, insertHandler);
		return myCompletionPolicy != null ? myCompletionPolicy.applyPolicy(result) : result;
	}


	public static LookupElement createKeywordLookupElement(@NotNull String keyword,
														   int priority,
														   @Nullable InsertHandler<LookupElement> insertHandler) {
		LookupElementBuilder builder = LookupElementBuilder.create(keyword).withBoldness(true).withInsertHandler(insertHandler);
		return PrioritizedLookupElement.withPriority(builder, priority);
	}

	@Nullable
	public static InsertHandler<LookupElement> createTemplateBasedInsertHandler(@NotNull String templateId) {
		return (context, item) -> {
			Template template = TemplateSettings.getInstance().getTemplateById(templateId);
			Editor editor = context.getEditor();
			if (template != null) {
				editor.getDocument().deleteString(context.getStartOffset(), context.getTailOffset());
				TemplateManager.getInstance(context.getProject()).startTemplate(editor, template);
			}
			else {
				int currentOffset = editor.getCaretModel().getOffset();
				CharSequence documentText = editor.getDocument().getImmutableCharSequence();
				if (documentText.length() <= currentOffset || documentText.charAt(currentOffset) != ' ') {
					EditorModificationUtil.insertStringAtCaret(editor, " ");
				}
				else {
					EditorModificationUtil.moveCaretRelatively(editor, 1);
				}
			}
		};
	}
}
