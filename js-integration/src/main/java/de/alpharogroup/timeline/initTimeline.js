var timeline = null;

google.load("visualization", "1");

// Set callback to run when API is loaded
google.setOnLoadCallback(drawVisualization);

// Called when the Visualization API is loaded.
function drawVisualization() {
	// Create and populate a data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'status', 7);
	data.addColumn('datetime', 'start');
	data.addColumn('datetime', 'end');
	data.addColumn('string', 'content');
	data.addColumn('string', 'group');

	var order = 1;
	for (var truck = 11; truck < 15; truck++) {
		var date = new Date(2010, 12, 14, 8, 0, 0);
		
		for (var i = 0; i < 10; i++) {
			date.setHours(date.getHours() + 4 * (Math.random() < 0.2));
			var start = new Date(date);

			date.setHours(date.getHours() + 2 + Math.floor(Math.random() * 4));
			var end = new Date(date);

			var orderText = "Lead" + order;
			if (Math.random() < 0.25) {
				orderText = "<img src='img/product-icon.png' style='width:32px; height:32px; vertical-align: middle'> "
						+ orderText;
			}
			orderText = "<div title='Order " + order + "' class='order'>"
					+ orderText + "</div>";

			var truckText = "<img src='img/truck-icon.png' style='width:24px; height:24px; vertical-align: middle'>"
					+ "Truck" + truck;
			var statusText = "<div>Status</div>";
			data.addRow([ statusText, start, end, orderText, truckText ]);
			order++;
		}
	}

	// specify options
	var options = {
		width : "100%",
		// height: "300px",
		height : "auto",
		layout : "box",
		editable : true,
		eventMargin : 5, // minimal margin between events
		eventMarginAxis : 0, // minimal margin beteen events and the axis
		showMajorLabels : false,
		axisOnTop : true,
		// groupsWidth : "200px",
		groupsChangeable : true,
		groupsOnRight : false,
		stackEvents : false
	};

	// Instantiate our timeline object.
	timeline = new links.Timeline(document.getElementById('mytimeline'),
			options);

	// Draw our timeline with the created data and options
	timeline.draw(data);
}