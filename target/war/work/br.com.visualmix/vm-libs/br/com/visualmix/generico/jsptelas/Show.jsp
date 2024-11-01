<%@ page language="java" %>
<%@ page session="true" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<jsp:useBean id='telas' scope='session' class='br.com.visualmix.generico.jsptelas.Telas' type="br.com.visualmix.generico.jsptelas.Telas" />
<html>
<script>
function mxc_evento(e)
{
	e = e? e: (window.event? window.event: null);

	this.posx = 0;
	this.posy = 0;

	if (e.pageX || e.pageY)
	{
		this.posx = e.pageX;
		this.posy = e.pageY;
	}
	else if (e.clientX || e.clientY)
	{
		this.posx = e.clientX + document.body.scrollLeft;
		this.posy = e.clientY + document.body.scrollTop;
	}

	if (e)
	{
		this.originalEvent = e;
		this.type = e.type;
		this.screenX = e.screenX;
		this.screenY = e.screenY;

		// IE  --> srcElement
		this.target = e.target? e.target: e.srcElement;

		// N4  --> modifiers
		if (e.modifiers)
		{
			this.altKey   = e.modifiers & Event.ALT_MASK;
			this.ctrlKey  = e.modifiers & Event.CONTROL_MASK;
			this.shiftKey = e.modifiers & Event.SHIFT_MASK;
			this.metaKey  = e.modifiers & Event.META_MASK;
		}
		else
		{
			this.altKey   = e.altKey;
			this.ctrlKey  = e.ctrlKey;
			this.shiftKey = e.shiftKey;
			this.metaKey  = e.metaKey;
		}

		// N4 --> which // N6 --> charCode
		this.charCode = !isNaN(e.charCode)? e.charCode: !isNaN(e.keyCode)? e.keyCode: e.which;
		this.keyCode = !isNaN(e.keyCode)? e.keyCode: e.which;
		this.button = !isNaN(e.button)? e.button: !isNaN(e.which)? e.which-1: null;
		this.debug = "charcode:" + e.charCode + " keycode:" + e.keyCode
     + " button:" + e.button + " wwhich:" + e.which;
	}

}

   function loadpage() {
      try {
           parent.desbloquear();
          } catch(e) {}           
   }
function keydowndocument(e) {
       var evento
	   evento = new mxc_evento(e);
	   if ((evento.ctrlKey) && (evento.shiftKey)) {
		   window.parent.callControl();
	   }
}        
document.onkeydown = keydowndocument; 


</script>
<%
out.print(telas.getHtml()); 
%>
</html>