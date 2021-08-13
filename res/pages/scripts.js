function getChildByClass(parent, theClassName) {
	for(var i = 0; i < parent.childNodes.length; i++) {
		if(parent.childNodes[i].className == theClassName) {
			return parent.childNodes[i];
		}
	}
}

// displaying media items
function generateMediaBox(path, fileType, maxWidth, maxHeight) { // !! TODO: let this work with file types that aren't just images
	// !! todo: let this work with file size
	var template = document.getElementById("mediaBoxTemplate").content.cloneNode(true);

	var mediaBox = getChildByClass(template, "mediaBox");

	var mediaText = getChildByClass(mediaBox, "mediaText");
	mediaText.innerHTML = path;

	var mediaImage = getChildByClass(mediaBox, "mediaImage");
	mediaImage.src = path;

	return template;
}