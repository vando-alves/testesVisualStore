const COOKIE_USERS   = "usuarios";
const COOKIE_ACESSOS = "ultimoAcesso";
const COOKIE_BIOVMIX = "bioVmix";
const COOKIE_STATUS  = "STATUS_SWITCH_BIOSTATUS";

function getCookie(nome) {
	nome += "=";
	var cookies = decodeURIComponent(document.cookie).split(";");
	
	for (var i = 0; i < cookies.length; i++) {
		var c = cookies[i];
		
		while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
		
		if (c.indexOf(nome) == 0) {
			return c.substring(nome.length, c.length);
		}
	}
	return null;
}