var count = [0,0,0,0,0]; //name, single line, multi line, radio button, slider
var names = ["name", "singleline", "multiline", "radio", "slider"]

function drag(event) {
	event.dataTransfer.setData("id", event.target.id);
}

function allowDrop(event) {
	event.preventDefault();
}

function drop(event) {

	event .preventDefault();
	var id = event.dataTransfer.getData("id");

	var block = document.createElement("div");
	block.setAttribute("class", "question");

	var ques = document.createElement("input");
	ques.setAttribute("class", "editable-input");
	ques.setAttribute("name", "ex");

	if( id == "tool-name") {
		ques.setAttribute("value", "What's your name-name");
		block.appendChild(ques);
		var inp = document.createElement("input");
		inp.setAttribute("type", "text");
		inp.setAttribute("placeholder", "Your name here");
		inp.setAttribute("class", "full-inp");
		block.appendChild(inp);
	} else if(id == "tool-slt") {
		ques.setAttribute("value", "Single line question-slt");
		block.appendChild(ques);
		var inp = document.createElement("input");
		inp.setAttribute("type", "text");
		inp.setAttribute("placeholder", "Single line answer");
		inp.setAttribute("class", "full-inp");
		block.appendChild(inp);
	} else if(id == "tool-mlt") {
		ques.setAttribute("value", "Multi line question-mlt");
		block.appendChild(ques);
		var inp = document.createElement("textarea");
		inp.setAttribute("placeholder", "Paragraph type answer");
		inp.setAttribute("class", "full-inp");
		block.appendChild(inp);
	} else if(id == "tool-s") {
		ques.setAttribute("value", "Slider-s");
		block.appendChild(ques);
		var inp = document.createElement("input");
		inp.setAttribute("type", "range");
		inp.setAttribute("class", "full-inp");
		block.appendChild(inp);
	}

	var sb = document.getElementById("submit-btn-main");

	if(sb)
		event.target.removeChild(document.getElementById("submit-btn-main"));
		
	event.target.appendChild(block);

	var inps = document.createElement("input");
	inps.setAttribute("type", "submit");
	inps.setAttribute("id", "submit-btn-main");
        inps.setAttribute("value", "Save form");

	event.target.appendChild(inps);
}

function setDragEvents() {
	var de = document.getElementsByClassName("tool");
	for(var i=0; i<de.length; i++)
		de[i].setAttribute("ondragstart", "drag(event)");
}

window.onload = function () {
	setDragEvents();
}