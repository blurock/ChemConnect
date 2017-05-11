// based on http://mbostock.github.com/d3/tutorial/bar-1.html
function d3_barchart(div, data) {
  
  var x = d3.scale.linear().domain([0, d3.max(data)]).range(["0px", "420px"]);
  
  var chart = d3.select(div).append("div").attr("class", "chart");
     
  chart.selectAll("div").data(data)
       .enter().append("div").style("width", x)
       .text(function(d) { return d; });
     
}
function d3_forcechart(div,links) {
		
	var nodes = {};

	// Compute the distinct nodes from the links.
	links.forEach(function(link) {
	  link.source = nodes[link.source] || (nodes[link.source] = {name: link.source});
	  link.target = nodes[link.target] || (nodes[link.target] = {name: link.target});
	});

		var width = 960,
		    height = 500;

		var force = d3.layout.force()
		    .nodes(d3.values(nodes))
		    .links(links)
		    .size([width, height])
		    .linkDistance(60)
		    .charge(-300)
		    .on("tick", tick)
		    .start();

		var svg = d3.select("div").append("svg")
		    .attr("width", width)
		    .attr("height", height);

		var link = svg.selectAll(".link")
		    .data(force.links())
		  .enter().append("line")
		    .attr("class", "link");

		var node = svg.selectAll(".node")
		    .data(force.nodes())
		  .enter().append("g")
		    .attr("class", "node")
		    .on("click", click)
		    .on("mouseover", mouseover)
		    .on("mouseout", mouseout)
		    .call(force.drag);
		

		node.append("circle")
		    .attr("r", 8);

		node.append("text")
		    .attr("x", 12)
		    .attr("dy", ".35em")
		    .text(function(d) { return d.name; });

		// Use elliptical arc path segments to doubly-encode directionality.
		function tick() {
		  path.attr("d", linkArc);
		  circle.attr("transform", transform);
		  text.attr("transform", transform);
		}
/*
		function linkArc(d) {
		  var dx = d.target.x - d.source.x,
		      dy = d.target.y - d.source.y,
		      dr = Math.sqrt(dx * dx + dy * dy);
		  return "M" + d.source.x + "," + d.source.y + "A" + dr + "," + dr + " 0 0,1 " + d.target.x + "," + d.target.y;
		}

		function transform(d) {
		  return "translate(" + d.x + "," + d.y + ")";
		}
*/
	
		function tick() {
		  link
		      .attr("x1", function(d) { return d.source.x; })
		      .attr("y1", function(d) { return d.source.y; })
		      .attr("x2", function(d) { return d.target.x; })
		      .attr("y2", function(d) { return d.target.y; });

		  node
		      .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
		}

		function mouseover() {
		  d3.select(this).select("circle").transition()
		      .duration(750)
		      .attr("r", 16);
		  d3.select(this).select("text").transition()
		  	.duration(750)
		  	.attr("dy",".70em")
		}
		function click() {
			 window.writeout(d3.select(this).select("text").text())
		}
		function mouseout() {
		  d3.select(this).select("circle").transition()
		      .duration(750)
		      .attr("r", 8);
		  d3.select(this).select("text").transition()
		  	.duration(750)
		  	.attr("dy",".35em")
		}
}
function d3_example() {
	// Set the dimensions of the canvas / graph
	var margin = {top: 30, right: 20, bottom: 30, left: 50},
	    width = 600 - margin.left - margin.right,
	    height = 270 - margin.top - margin.bottom;

	// Parse the date / time
	var parseDate = d3.time.format("%d-%b-%y").parse;

	// Set the ranges
	var x = d3.time.scale().range([0, width]);
	var y = d3.scale.linear().range([height, 0]);

	// Define the axes
	var xAxis = d3.svg.axis().scale(x)
	    .orient("bottom").ticks(5);

	var yAxis = d3.svg.axis().scale(y)
	    .orient("left").ticks(5);

	// Define the line
	var valueline = d3.svg.line()
	    .x(function(d) { return x(d.date); })
	    .y(function(d) { return y(d.close); });
	    
	// Adds the svg canvas
	var svg = d3.select("body")
	    .append("svg")
	        .attr("width", width + margin.left + margin.right)
	        .attr("height", height + margin.top + margin.bottom)
	    .append("g")
	        .attr("transform", 
	              "translate(" + margin.left + "," + margin.top + ")");

	// Get the data
	d3.csv("data/data.csv", function(error, data) {
	    data.forEach(function(d) {
	        d.date = parseDate(d.date);
	        d.close = +d.close;
	    });

	    // Scale the range of the data
	    x.domain(d3.extent(data, function(d) { return d.date; }));
	    y.domain([0, d3.max(data, function(d) { return d.close; })]);

	    // Add the valueline path.
	    svg.append("path")
	        .attr("class", "line")
	        .attr("d", valueline(data));

	    // Add the X Axis
	    svg.append("g")
	        .attr("class", "x axis")
	        .attr("transform", "translate(0," + height + ")")
	        .call(xAxis);

	    // Add the Y Axis
	    svg.append("g")
	        .attr("class", "y axis")
	        .call(yAxis);

	});	
}
