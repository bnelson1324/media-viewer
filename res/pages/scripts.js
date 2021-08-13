function getChildByClass(parent, theClassName) {
	for(var i = 0; i < parent.childNodes.length; i++) {
		if(parent.childNodes[i].className == theClassName) {
			return parent.childNodes[i];
		}
	}
}