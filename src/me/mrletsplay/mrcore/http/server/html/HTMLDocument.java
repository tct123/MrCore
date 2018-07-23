package me.mrletsplay.mrcore.http.server.html;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import me.mrletsplay.mrcore.http.server.HttpStatusCode;
import me.mrletsplay.mrcore.http.server.css.CSSStyleElement;
import me.mrletsplay.mrcore.http.server.css.CSSStylesheet;
import me.mrletsplay.mrcore.http.server.js.JSScript;

public class HTMLDocument {

	private HttpStatusCode statusCode;
	private List<HTMLElement> elements;
	private JSScript baseScript;
	private CSSStylesheet style;
	
	public HTMLDocument() {
		this(HttpStatusCode.OKAY_200);
	}
	
	public HTMLDocument(HttpStatusCode statusCode) {
		this.statusCode = statusCode;
		this.elements = new ArrayList<>();
		this.baseScript = new JSScript();
		this.style = new CSSStylesheet();
	}
	
	public void addElement(HTMLElement element) {
		elements.add(element);
	}
	
	public CSSStylesheet getStyle() {
		return style;
	}
	
	public JSScript getBaseScript() {
		return baseScript;
	}
	
	public List<HTMLElement> getElements() {
		return elements;
	}
	
	public HttpStatusCode getStatusCode() {
		return statusCode;
	}
	
	public HTMLDocument appendDocument(HTMLDocument doc) {
		elements.addAll(doc.elements);
		style.appendStylesheet(doc.style);
		baseScript.appendScript(doc.baseScript);
		return this;
	}
	
	public HTMLBuiltDocument build(String... params) {
		JSScript script = baseScript.clone();
		CSSStylesheet style = this.style.clone();
		StringBuilder builder = new StringBuilder();
		AtomicInteger uID = new AtomicInteger(0);
		
		StringBuilder body = new StringBuilder();
		for(HTMLElement el : elements ) {
			appendElement(body, script, style, el, uID, params);
		}
		
		builder.append("<head>");
		builder.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://fonts.googleapis.com/css?family=Ek+Mukta\">");
		builder.append("<style>");
		builder.append(style.asString());
		builder.append("</style>");
		builder.append("</head>");
		
		builder.append("<body>");
		builder.append(body);
		builder.append("<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"></script>");
		builder.append("<script src=\"https://graphite-official.com/api/mrcore/files/http_client_impl.js\"></script>");
		builder.append("<script>");
		builder.append(script.asString());
		builder.append("</script>");
		builder.append("</body>");
		return new HTMLBuiltDocument(this, script, builder.toString());
	}
	
	private void appendElement(StringBuilder builder, JSScript script, CSSStylesheet style, HTMLElement el, AtomicInteger uID, String... params) {
		HTMLElement.OnHover onHover = el.onHover();
		HTMLElement.OnClicked onClicked = el.onClicked();
		if(el.getID() == null) el.setID("el_"+uID.get());
		uID.set(uID.get() + 1);
		if(!el.css().isEmpty()) {
			style.addElement("#" + el.getID(), el.css().clone());
		}
		if(!onHover.css().isEmpty()) {
			CSSStyleElement stl = onHover.css().clone();
			style.addElement("#" + el.getID() + ":hover", stl);
		}
		if(!onClicked.css().isEmpty()) {
			CSSStyleElement stl = onClicked.css().clone();
			style.addElement("#" + el.getID() + ":active", stl);
		}
		if(el.getType() != null) {
			builder.append("<").append(el.getType()).append(el.getClasses().isEmpty()? "" : " class=\"" + el.getClasses().stream().collect(Collectors.joining(" ")) + "\"");
			if(el.getID() != null) {
				builder.append(" id=\""+el.getID()+"\"");
			}
			if(onClicked.getFunction() != null) {
				script.appendFunction(onClicked.getFunction());
				builder.append(" onclick=").append(onClicked.getFunction().getName()).append("()");
			}
			if(onHover.getFunction() != null) {
				script.appendFunction(onHover.getFunction());
				builder.append(" onmouseover=").append(onHover.getFunction()).append("()");
			}
			builder.append(">");
		}
		String content = el.getContent();
		for(int i = 0; i < params.length; i+=2) {
			content = content.replace(params[i], params[i+1]);
		}
		builder.append(content);
		el.getChildren().forEach(c -> appendElement(builder, script, style, c, uID, params));
		if(el.getType() != null) {
			builder.append("</").append(el.getType()).append(">");
		}
	}
	
	public static class HTMLBuiltDocument {
		
		private HTMLDocument base;
		private JSScript script;
		private String htmlCode;
		
		public HTMLBuiltDocument(HTMLDocument base, JSScript script, String htmlCode) {
			this.base = base;
			this.script = script;
			this.htmlCode = htmlCode;
		}
		
		public HttpStatusCode getStatusCode() {
			return base.getStatusCode();
		}
		
		public HTMLDocument getBase() {
			return base;
		}
		
		public JSScript getScript() {
			return script;
		}
		
		public String getHTMLCode() {
			return htmlCode;
		}
		
	}
	
}
