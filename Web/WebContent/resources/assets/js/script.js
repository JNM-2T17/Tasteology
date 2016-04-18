var incentiveFields = {};
var contactInfoFields = {};

function checkSource(id) {
	var found = false;
	var temp;
	if( id === "incentive" ) {
		temp = incentiveFields;
	} else if( id === "contact") {
		temp = contactInfoFields;
	}

	for(v in temp) {
		if( temp[v] == true ) {
			found = true;
			temp[v] = false;
		}
	}

	return !found;
}

function removeIncentive(x) {
	x = x.parentNode;
	x.parentNode.removeChild(x);
}

function showIncent() {
	document.getElementById("coursecode").value = "";
	document.getElementById("section").value = "";
	var v = document.getElementById("add-incentive-form").style;
	v.display = v.display == "block" ? "none" : "block";
}

function addIncentive(source) {
	if(source) {
		incentiveFields[source.id] = true;
	}
	var course = document.getElementById("coursecode").value;
	var section = document.getElementById("section").value;
	if( /^[A-Z0-9-]{7}$/.test(course) 
		&& /^([ACKVNLS][0-9]{2}|[E][A-Z])$/.test(section) ) {
		document.getElementById("incentive-list").innerHTML 
			+= "<li>" + course + " " + section 
				+ " <i class=\"fa fa-trash\" \
				onclick=\"removeIncentive(this);\"></i></li>";
		document.getElementById("add-incentive-form").style.display 
			= "none";
	} else {
		$("#incentive-error").fadeIn();
	}
}

function removeContact(x) {
	x = x.parentNode;
	x.parentNode.removeChild(x);
}

function showContactInfo() {
	document.getElementById("contact-type").value = "E-mail";
	document.getElementById("contact").value = "";
	var v = document.getElementById("add-contact-info-form").style;
	v.display = v.display == "block" ? "none" : "block";
}

function addContactInfo(source) {
	if( source ) {
		contactInfoFields[source.id] = true;
	}
	var type = document.getElementById("contact-type").value;
	var info = document.getElementById("contact").value;
	var regex;
	var fa_class;
	if( type === "E-mail") {
		regex = /^[a-z0-9-_.]*@[a-z0-9-_.]*[^.]$/i;
		fa_class = "fa-envelope";
	} else if( type === "Landline" ) {
		regex = /^\(?\+?[0-9]{0,2}\)?[0-9]{3}(\-|\s)?[0-9]{2}(\-|\s)?[0-9]{2}?$/;
		fa_class = "fa-phone";
	} else if( type === "Mobile") {
		regex = /^\(?\+?[0-9]{0,2}\)?0?[0-9]{3}(\-|\s)?[0-9]{3}(\-|\s)?[0-9]{4}$/;
		fa_class = "fa-mobile";
	} else {
		regex = true;
	}

	if( regex === true || regex.test(info) ) {
		document.getElementById("contact-info").innerHTML 
				+= "<li><i class='fa " + fa_class + "'></i> " + info
					+ " <i class=\"fa fa-trash\" \
					onclick=\"removeContact(this);\"></i></li>";
		document.getElementById("add-contact-info-form").style.display 
				= "none";
	} else {
		$("#contact-error").fadeIn();
	}
}