window.z = window.z || {};

z.dialog = function(settings) {
	var options = $.extend({
		className: '',
		msg: '',
		btns: []
	}, settings);

	var btnTpl = '',
		item;
	for(var i = 0; i < options.btns.length; i++) {
		item = options.btns[i];
		btnTpl += '<a href="javascript:;" class="zdialog_action_btn ' + item.class + '" data-index="' + i + '">' + item.name + '</a>';
	}

	var dialog = $('<div class="zdialog ' + options.className + '">' +
		'<div class="zdialog_mask"></div>' +
		'<div class="zdialog_panel">' +
		'<div class="zdialog_content">' + options.msg + '</div>' +
		'<div class="zdialog_action">' + btnTpl + '</div>' +
		'</div>' +
		'</div>').appendTo('body');

	dialog.on('click', '.zdialog_action_btn', function() {
		var fn = options.btns[this.dataset['index']].fn;
		if(typeof(fn) == 'function') {
			fn(dialog);
		}
		dialog.remove();
	});

	return dialog;
}

z.confirm = function(msg, funOk, funCancel) {
	var options = {
		className: 'zdialog_confirm',
		msg: msg,
		btns: [{
			name: '确定',
			class: 'primary',
			fn: function(dialog) {
				if(typeof(funOk) == 'function') {
					funOk(dialog);
				}
			}
		}, {
			name: '取消',
			fn: function(dialog) {
				if(typeof(funCancel) == 'function') {
					funCancel(dialog);
				}
			}
		}]
	};

	z.dialog(options);
}

z.alert = function(msg, funOk) {
	var options = {
		className: 'zdialog_alert',
		msg: msg,
		btns: [{
			name: '确认',
			class: 'primary',
			fn: function(dialog) {
				if(typeof(funOk) == 'function') {
					funOk(dialog);
				}
			}
		}]
	};

	z.dialog(options);
}

z.tips = function(msg, time) {
	var options = {
		className: 'zdialog_tips',
		msg: msg
	};

	var dialog = z.dialog(options);
	time = time ? time : 2;
	setTimeout(function() {
		dialog.remove();
	}, time * 1000);
}