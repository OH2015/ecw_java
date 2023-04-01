/* global Chart:false */

$(function() {
	'use strict'


	var orderQuantityDaily = [];
	var earningsDaily = [];
	var orderDateList = [];


	for (let i = 0; i < orderList.length; i++) {
		var order = orderList[i];
		orderQuantityDaily.push(order.orderQuantityDaily);
		earningsDaily.push(order.earningsDaily);
		orderDateList.push(order.orderDate);
	}

	var ticksStyle = {
		fontColor: '#495057',
		fontStyle: 'bold'
	}

	var mode = 'index'
	var intersect = true

	var $salesChart = $('#sales-chart')
	// eslint-disable-next-line no-unused-vars
	var salesChart = new Chart($salesChart, {
		type: 'bar',
		data: {
			labels: orderDateList,
			datasets: [
				{
					backgroundColor: '#007bff',
					borderColor: '#007bff',
					data: earningsDaily
				},
			]
		},
		options: {
			maintainAspectRatio: false,
			tooltips: {
				mode: mode,
				intersect: intersect
			},
			hover: {
				mode: mode,
				intersect: intersect
			},
			legend: {
				display: false
			},
			scales: {
				yAxes: [{
					// display: false,
					gridLines: {
						display: true,
						lineWidth: '10px',
						color: 'rgba(0, 0, 0, .2)',
						zeroLineColor: 'transparent'
					},
					ticks: $.extend({
						beginAtZero: true,

						// Include a dollar sign in the ticks
						callback: function(label, index, labels) {
							return label.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + ' 円';
						}
					}, ticksStyle)
				}],
				xAxes: [{
					display: true,
					gridLines: {
						display: false
					},
					ticks: ticksStyle
				}]
			}
		}
	})


})



// lgtm [js/unused-local-variable]
