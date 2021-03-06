<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function moveSelect(sl1, sl2,type) {
	if( sl1 == undefined || sl1 == null) return;
    if( sl2 == undefined || sl2 == null) return;
    for (var i = 0; i < sl1.length; ++i){
       if (sl1.options[i].selected) {
	       if (findOption(sl1.options[i].value, sl2)){
	          sl1.options[i] = null;
	          --i;
	          continue;
	       }
	       var pp=document.getElementById(type+'type');
	       moveOption(sl1, sl2, i, false);
	       moveOption(sl1, pp, i, true);              
	       --i;                
       }               
    }
}

function deleteSelect(sl1, sl2, type) {
	if( sl1 == undefined || sl1 == null) return;
	if( sl2 == undefined || sl2 == null) return;
	for (var i = 0; i < sl2.length; ++i){
	    if (sl2.options[i].selected) {
	        if (findOption(sl2.options[i].value, sl1)) {
	            sl2.options[i] = null;
	            --i;
	            continue;
	        }
	        moveOption(sl2, sl1, i, true);
	        --i;
	   }  
	}
}

function findOption(op, sl) {
	if( sl == undefined || sl == null) return false;
	for (var i = 0; i < sl.length; ++i)
	    if (sl.options[i].value == op) return true;
	return false;
}

function moveOption(sl1, sl2, i) {
	if( sl1 == undefined || sl1 == null) return;
	if( sl2 == undefined || sl2 == null) return;
	if (i < sl1.length) {
		var newOption = new Option(sl1.options[i].text, sl1.options[i].value);
		newOption.selected=true;
		sl2.options.add(newOption);
		newOption.title=sl1.options[i].title;
	}
} 

function moveSelectAll(sl1, sl2, type) {
    if( sl1 == undefined || sl1 == null) return;
    if( sl2 == undefined || sl2 == null) return;
    for (var i = 0; i < sl1.length; ++i){
		if (findOption(sl1.options[i].value, sl2)){ 
		    sl1.options[i] = null;
		    --i;
		    continue;
		}
		var pp=document.getElementById(type+'type');
		moveOptionAll(sl1, sl2, i, false);
		moveOptionAll(sl1, pp, i, true);              
		--i;                
    }
}

function moveOptionAll(sl1, sl2, i, flagDel) {
	if( sl1 == undefined || sl1 == null) return;
	if( sl2 == undefined || sl2 == null) return;
	if (i < sl1.length) {
	    var newOption = new Option(sl1.options[i].text, sl1.options[i].value);
	    newOption.selected=true;
	    sl2.options.add(newOption);
		newOption.title=sl1.options[i].title;
	    if (flagDel) sl1.options[i] = null;
	}
} 

function deleteSelectAll(sl1, sl2, type) {
	if( sl1 == undefined || sl1 == null) return;
	if( sl2 == undefined || sl2 == null) return;
	for (var i = 0; i < sl2.length; ++i){
		if (findOption(sl2.options[i].value, sl1)) {
			sl2.options[i] = null;
			--i;
			continue;
		}
		moveOptionAll(sl2, sl1, i, true);
		document.getElementById(type+'type').options[i]=null;
		--i;
	}
}

</script>