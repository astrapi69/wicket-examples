var Wicket;
if (!Wicket) {
	Wicket = {};
} else {
	if (typeof Wicket != "object") {
		throw new Error("Wicket already exists and is not an object");
	}
}

alert("${message}");
