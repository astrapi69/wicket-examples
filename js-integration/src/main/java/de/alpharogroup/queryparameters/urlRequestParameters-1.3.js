jQuery.extend({
	/**
	 * Returns all request parameters in an associative array.
	 * @return All request parameters in an associative array or null if no request parameters does not exists.
	 *
	 * @example var parametersArray = $.getRequestParameters();
	 **/
	getRequestParameters: function(){
	  var strReturn = "";
	  var strSearch = window.location.search;
	  var keyValuePairs = [], keyValuePair;
	  if (window.location.search != ""){
	    // remove the question mark (?)
	    var strQueryString = window.location.search.substring(1);
	    var queryString = strQueryString.split("&");
		for (i=0;i<queryString.length;i++){
		keyValuePair = queryString[i].split('=');
        keyValuePairs.push(keyValuePair[0]);
        keyValuePairs[keyValuePair[0]] = keyValuePair[1];
		}
	  }
	  return keyValuePairs;
	},
	
	/**
	 * Returns all request parameters in an associative array.
	 * @param key The key from the parameter.
	 * @return The value from the given key or null if no parameter exists with the given key.
	 *
	 * @example var value = $.getRequestParameter('key');
	 **/
	getRequestParameter: function(key){
	  return $.getRequestParameters()[key];
	},
	
	/**
	 * Creates a query string from the given associative array that represents request parameters and returns it.
	 * If the given array is empty an empty string is returned.
	 * @param parameters The associative array that represents request parameters.
	 * @return Returns the created query string or an empty string if the given array is empty.
	 *
	 * @example var parameters = $.getRequestParameters();
	 *          var queryString = $.createQueryStringFromParameters(parameters);
	 *          var strSearch = window.location.search;
	 *          var equal = queryString == strSearch; // equal is true          
	 **/
	createQueryStringFromParameters: function(parameters) {
		var qs = "";
		for(var k in parameters){
			qs += k +"="+parameters[k]+"&";
		}
		if(0<qs.length){
			qs = qs.substring(0, qs.length-1);
			qs = "?"+qs;
		}
		return qs;
	}

});