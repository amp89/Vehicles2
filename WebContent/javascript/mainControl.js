$(document).ready(function() {
	console.log("control.js loaded");

	var $mainDiv = $("#main");

	// html forms
	var doSearch = function() {
		// ajax call with a callback to load data

		 var $formValues = $("#searchForm").serializeArray();
		 
		 for(var i = 0; i < $formValues.length; i++){
			 console.log($formValues[i]);
		 }
		 
		 var object = {}
		 $.each($formValues,function(){
			 object[this.name] = this.value;
		 });
		 
		 
		 
		 var serachData = JSON.stringify(object);
		 
		 $.ajax({
			 type:"post",
			 url:"rest/searchVehicles",
			 contentType : 'application/json; charset=utf-8',
			 data:serachData,
			 //dataType:"json"
			 dataType:"json"
		 }).done(function(data){
			 console.log(data);
			 //nothing actually comes here
			 iterateSearchResults(data);
		 }).fail(function(){
			 console.log("update failed");
		 });

	}
	
	var iterateSearchResults = function(data){
		var searchResults = data;
		for(var i = 0; i < searchResults.length; i++){
			var result = $.parseJSON(searchResults[i]);
			var resultId = result._id;
			var year = result.year;
			var make = result.make;
			var model = result.model;
			
			
			$resultBody = $("#resultBody");
			$resultBody.append("<div id="+resultId+ ">"+year + "  " + make  + "  "  + model +"</div>");
		}
		
	}

	var addNew = function() {
		// ajax call w/ callback to load data
	}

	$("#searchButton").on('click', function(event) {
		event.preventDefault();
		$mainDiv.empty();
		$mainDiv.load("htmlParts/searchForm.html",null,function(){
			
			
			$("#submitSearch").on('click', function(event) {
				event.preventDefault();
				console.log("search submitted");
				doSearch();
				
			});
			
		});
		

	});
	
	
	$("#addButton").on('click', function(event) {
		event.preventDefault();
		$mainDiv.empty();
		$mainDiv.load("htmlParts/addForm.html");
		
		$("#submitNew").on('click', function(event) {
			event.preventDefault();
			console.log("new submitted");
			addNew();
			
		});
		
	});
	
	

});
