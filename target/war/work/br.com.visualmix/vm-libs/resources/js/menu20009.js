// Title: tigra menu
// Description: See the demo at url
// URL: http://www.javascript-menu.com/
// Version: 2.0 (commented source)
// Date: 04-05-2003 (mm-dd-yyyy)
// Tech. Support: http://www.softcomplex.com/forum/forumdisplay.php?fid=40
// Notes: This script is free. Visit official site for further details.

// --------------------------------------------------------------------------------
// global collection containing all menus on current page
var A_MENUS = [];

/*
  --- menu level scope settins structure --- 
  note that this structure has changed its format since previous version.
  Now this structure has the same layout as Tigra Menu GOLD.
  Format description can be found in product documentation.
*/

var MENU_AjusteMSIE = 2;  /* Ajuste no calculo do TOP para o internet explorer */
var MENU_AjusteWMSIE = 4;  /* Ajuste no calculo do TOP para o internet explorer */
var MENU_NoChild = "_nochild"; /* Complemento no Css para items sem filhos */
var MENU_POS = [{
	// item sizes
	'height': 20,
	'width': 111,
	// menu block offset from the origin:
	//	for root level origin is upper left corner of the page
	//	for other levels origin is upper left corner of parent item
	'block_top': 99,
	'block_left': 006,
	// offsets between items of the same level
	'top': 22,
	'left': 0,
	// time in milliseconds before menu is hidden after cursor has gone out
	// of any items
	'hide_delay': 200,
	'expd_delay': 200,
	'css' : {
		'outer' : ['m0l0oout', 'm0l0oover'],
		'inner' : ['m0l0iout', 'm0l0iover']
	}
},{
	'height': 20,
	'width': 150,
	'block_top': 5,
	'block_left': 108,
	'top': 22,
	'left': 0,
	'css' : {
		'outer' : ['m0l1oout', 'm0l1oover'],
		'inner' : ['m0l1iout', 'm0l1iover']
	}
},{
	'block_top': 5,
	'block_left': 146
}
]

// --------------------------------------------------------------------------------
// menu class
function browsername() {

	if (navigator.userAgent.indexOf("Opera") >= 0)
	 return "opera";
	else if (navigator.userAgent.indexOf("obot") >= 0)
	 return "robot";
	else if (navigator.appName.indexOf("etscape") >= 0)
	 return "netscape"
	else if (navigator.appName.indexOf("icrosoft") >= 0)
	 return "msie"

    return "unknown";
}

function menu (a_items, a_tpl) {

	// browser check
	if (!document.body || !document.body.style)
		return;

	// store items structure
	this.a_config = a_items;

	// store template structure
	this.a_tpl = a_tpl;

	// get menu id
	this.n_id = A_MENUS.length;

	// declare collections
	this.a_index = [];
	this.a_children = [];

	// assigh methods and event handlers
	this.expand      = menu_expand;
	this.collapse    = menu_collapse;

	this.onclick     = menu_onclick;
	this.onmouseout  = menu_onmouseout;
	this.onmouseover = menu_onmouseover;
	this.onmousedown = menu_onmousedown;

	// default level scope description structure 
	this.a_tpl_def = {
		'block_top'  : 16,
		'block_left' : 16,
		'top'        : 20,
		'left'       : 4,
		'width'      : 120,
		'height'     : 22,
		'hide_delay' : 0,
		'expd_delay' : 0,
		'css'        : {
			'inner' : '',
			'outer' : ''
		}
	};
	
	// assign methods and properties required to imulate parent item
	this.getprop = function (s_key) {
		return this.a_tpl_def[s_key];
	};

	this.o_root = this;
	this.n_depth = -1;
	this.n_x = 0;
	this.n_y = 0;

	// 	init items recursively
    document.write (
		"<iframe border=0 id='menu_back' width='100%' height='100%' src='' style='filter:alpha(opacity=0);opacity=0.0;-moz-opacity:0.0;border:0;visibility:hidden;position:absolute;left:0;top:0;'></iframe>\n"
        );
	for (n_order = 0; n_order < a_items.length; n_order++)
		new menu_item(this, n_order,0);

	// register self in global collection
	A_MENUS[this.n_id] = this;

	// make root level visible
    document.getElementById('menu_back').style.visibility = 'hidden';
	for (var n_order = 0; n_order < this.a_children.length; n_order++)
		this.a_children[n_order].e_oelement.style.visibility = 'visible';
}

// --------------------------------------------------------------------------------
function menu_collapse (n_id) {
	// cancel item open delay
	clearTimeout(this.o_showtimer);

	// by default collapse to root level
	var n_tolevel = (n_id ? this.a_index[n_id].n_depth : 0);
	
	// hide all items over the level specified
	for (n_id = 0; n_id < this.a_index.length; n_id++) {
		var o_curritem = this.a_index[n_id];
		if (o_curritem.n_depth > n_tolevel && o_curritem.b_visible) {
			o_curritem.e_oelement.style.visibility = 'hidden';
			o_curritem.b_visible = false;
		}
	}

	document.getElementById('menu_back').style.visibility = 'hidden';

	// reset current item if mouse has gone out of items
	if (!n_id)
		this.o_current = null;
}

// --------------------------------------------------------------------------------
function menu_expand (n_id) {

	// expand only when mouse is over some menu item
	if (this.o_hidetimer)
		return;

	// lookup current item
	var o_item = this.a_index[n_id];

	// close previously opened items
	if (this.o_current && this.o_current.n_depth >= o_item.n_depth)
		this.collapse(o_item.n_id);
	this.o_current = o_item;

	if (browsername() == "msie")
	   { 
	    document.getElementById('menu_back').style.visibility = 'visible';
	}		

	// exit if there are no children to open
	if (!o_item.a_children)
		return;

	// show direct child items
	for (var n_order = 0; n_order < o_item.a_children.length; n_order++) {
		var o_curritem = o_item.a_children[n_order];
		o_curritem.e_oelement.style.visibility = 'visible';
		o_curritem.b_visible = true;
	}
}

// --------------------------------------------------------------------------------
//
// --------------------------------------------------------------------------------
function menu_onclick (n_id) {
	if (Boolean(this.a_index[n_id].a_config[1]))
	{
  	   // collapse if item has link defined
        this.collapse();
		return true;
	} else {
   	   // don't go anywhere if item has no link defined
		return false;
	}
}

// --------------------------------------------------------------------------------
function menu_onmouseout (n_id) {

	// lookup new item's object	
	var o_item = this.a_index[n_id];

	// apply rollout
	o_item.e_oelement.className = o_item.getstyle(0, 0);
	o_item.e_ielement.className = o_item.getstyle(1, 0);
	
	// update status line	
	o_item.upstatus(7);

	// run mouseover timer
	this.o_hidetimer = setTimeout('A_MENUS['+ this.n_id +'].collapse();',
		o_item.getprop('hide_delay'));
}

// --------------------------------------------------------------------------------
function menu_onmouseover (n_id) {

	// cancel mouseoute menu close and item open delay
	clearTimeout(this.o_hidetimer);
	this.o_hidetimer = null;
	clearTimeout(this.o_showtimer);

	// lookup new item's object	
	var o_item = this.a_index[n_id];

	// update status line	
	o_item.upstatus();

	// apply rollover
	o_item.e_oelement.className = o_item.getstyle(0, 1);
	o_item.e_ielement.className = o_item.getstyle(1, 1);
	
	// if onclick open is set then no more actions required
	if (o_item.getprop('expd_delay') < 0)
		return;

	// run expand timer
	this.o_showtimer = setTimeout('A_MENUS['+ this.n_id +'].expand(' + n_id + ');',
		o_item.getprop('expd_delay'));

}

// --------------------------------------------------------------------------------
// called when mouse button is pressed on menu item
// --------------------------------------------------------------------------------
function menu_onmousedown (n_id) {
	
	// lookup new item's object	
	var o_item = this.a_index[n_id];

	// apply mouse down style
	o_item.e_oelement.className = o_item.getstyle(0, 2);
	o_item.e_ielement.className = o_item.getstyle(1, 2);

	this.expand(n_id);
//	this.items[id].switch_style('onmousedown');
}


// --------------------------------------------------------------------------------
// menu item Class
function menu_item (o_parent, n_order, n_items) {

	// store parameters passed to the constructor
	this.n_depth  = o_parent.n_depth + 1;
	this.a_config = o_parent.a_config[n_order + (this.n_depth ? 3 : 0)];

	// return if required parameters are missing
	if (!this.a_config) return;

	// store info from parent item
	this.o_root    = o_parent.o_root;
	this.o_parent  = o_parent;
	this.n_order   = n_order;

	// register in global and parent's collections
	this.n_id = this.o_root.a_index.length;
	this.o_root.a_index[this.n_id] = this;
	o_parent.a_children[n_order] = this;

	// calculate item's coordinates
	var o_root = this.o_root,
		a_tpl  = this.o_root.a_tpl;

	// assign methods
	this.getprop  = mitem_getprop;
	this.getstyle = mitem_getstyle;
	this.upstatus = mitem_upstatus;

    ajuste = 0;
    ajustew = 0;
	if (browsername() == "msie")
    { 
	   ajuste = MENU_AjusteMSIE;
	   ajustew = MENU_AjusteWMSIE;
    }	
	
	var valor = 0;	
	if (n_order==0)
	{
		// Se primeiro item verica se nao vai ficar fora da tela
		valor = o_parent.n_y + this.getprop('block_top') + (this.getprop('height') * (n_items+1));
		if (valor > document.body.clientHeight)
		{
			valor = valor - document.body.clientHeight;
		}  else {
			valor = 0
		}
	}

	this.n_x = n_order
		? o_parent.a_children[n_order - 1].n_x + this.getprop('left')
		: o_parent.n_x + this.getprop('block_left');


	this.n_y = n_order
		? o_parent.a_children[n_order - 1].n_y + (this.getprop('top') - ajuste)
		: o_parent.n_y + this.getprop('block_top') - valor;
	if (this.n_y < 0)
	{ this.n_y = 0;
	}

	// generate item's HMTL
	document.write (
		'<a id="e' + o_root.n_id + '_'
			+ this.n_id +'o" class="' + this.getstyle(0, 0) + '" href="' + this.a_config[1] + '"'
			+ (this.a_config[2] && this.a_config[2]['tw'] ? ' target="'
			+ this.a_config[2]['tw'] + '"' : '')
			+ (this.a_config[2] && this.a_config[2]['tt'] ? ' title="'
			+ this.a_config[2]['tt'] + '"' : '') + ' style="position: absolute; top: '
			+ (this.n_y) + 'px; left: ' + this.n_x + 'px; width: '
			+ (this.getprop('width') + ajustew) + 'px; height: '
			+ this.getprop('height') + 'px; visibility: hidden;'
			+' z-index: ' + this.n_depth + ';" '
			+ 'onclick="return A_MENUS[' + o_root.n_id + '].onclick('
			+ this.n_id + ');" onmouseout="A_MENUS[' + o_root.n_id + '].onmouseout('
			+ this.n_id + ');" onmouseover="A_MENUS[' + o_root.n_id + '].onmouseover('
			+ this.n_id + ');" onmousedown="A_MENUS[' + o_root.n_id + '].onmousedown('
			+ this.n_id + ');"><div  id="e' + o_root.n_id + '_'
			+ this.n_id +'i" class="' + this.getstyle(1, 0) + '">'
			+ this.a_config[0] + "</div></a>\n"
		);
	this.e_ielement = document.getElementById('e' + o_root.n_id + '_' + this.n_id + 'i');
	this.e_oelement = document.getElementById('e' + o_root.n_id + '_' + this.n_id + 'o');

	this.b_visible = !this.n_depth;

	// no more initialization if leaf
	if (this.a_config.length < 4)
		return;

	// node specific methods and properties
	this.a_children = [];

	// init downline recursively
	for (var n_order = 0; n_order < this.a_config.length - 3; n_order++)
		new menu_item(this, n_order,this.a_config.length - 3);

	this.e_oelement.className = this.getstyle(0, 0);
	this.e_ielement.className = this.getstyle(1, 0);

}

// --------------------------------------------------------------------------------
// reads property from template file, inherits from parent level if not found
// ------------------------------------------------------------------------------------------
function mitem_getprop (s_key) {

	// check if value is defined for current level
	var s_value = null,
		a_level = this.o_root.a_tpl[this.n_depth];

	// return value if explicitly defined
	if (a_level)
		s_value = a_level[s_key];

	// request recursively from parent levels if not defined
	return (s_value == null ? this.o_parent.getprop(s_key) : s_value);
}
// --------------------------------------------------------------------------------
// reads property from template file, inherits from parent level if not found
// ------------------------------------------------------------------------------------------
function mitem_getstyle (n_pos, n_state) {

	var a_css = this.getprop('css');
	var a_oclass = a_css[n_pos ? 'inner' : 'outer'];
	var child = "";

	if (!this.a_children)
	{
		child = MENU_NoChild;
	}

	// same class for all states	
	if (typeof(a_oclass) == 'string') {
	   return a_oclass + child;
	}

	// inherit class from previous state if not explicitly defined
	for (var n_currst = n_state; n_currst >= 0; n_currst--)
		if (a_oclass[n_currst])		    
			return a_oclass[n_currst] + child;
}

// ------------------------------------------------------------------------------------------
// updates status bar message of the browser
// ------------------------------------------------------------------------------------------
function mitem_upstatus (b_clear) {
	window.setTimeout("window.status=unescape('" + (b_clear
		? ''
		: (this.a_config[2] && this.a_config[2]['sb']
			? escape(this.a_config[2]['sb'])
			: escape(this.a_config[0]) + (this.a_config[1]
				? ' ('+ escape(this.a_config[1]) + ')'
				: ''))) + "')", 10);
}

// --------------------------------------------------------------------------------
// that's all folks

