/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
google.load("visualization", "1", {
	packages:["timeline"]
});

google.setOnLoadCallback(drawChart);

function drawChart() {
	var container = document.getElementById('chart_div');
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();

    dataTable.addColumn({ type: 'string', id: 'President' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
    dataTable.addColumn({ type: 'date', id: 'End' });
    dataTable.addRows([
      [ 'Washington', new Date(1789, 3, 29), new Date(1797, 2, 3) ],
      [ 'Adams',      new Date(1797, 2, 3),  new Date(1801, 2, 3) ],
      [ 'Jefferson',  new Date(1801, 2, 3),  new Date(1809, 2, 3) ]]);

    chart.draw(dataTable);
}

