/**
 * Created by yasuhiro on 2014/05/05.
 */
function Calendar( _year, _month ) {
  this.year  = _year;
  this.month = _month;
  this.dates = [];
  if (! (_year >= 1970 && _year <= 9999 && _month >= 0 && _month < 12) ) {
    var toDay      = new Date();
    this.year  = toDay.getFullYear();
    this.month = toDay.getMonth();
  }

  var firstDayOfTheMonth = new Date(this.year, this.month, 1)
  var start_day = new Date(firstDayOfTheMonth.getTime() + (Calendar.TIME_OF_DAY * firstDayOfTheMonth.getDay() * -1))

  this.dates.push(start_day);
  var days_from_start = 1;
  for (var days_from_start = 1; days_from_start < Calendar.DAYS_IN_MONTHLY_CALENDAR; days_from_start++ ) {
    this.dates.push(new Date(start_day.getTime() + Calendar.TIME_OF_DAY * days_from_start));
  }
}

Calendar.TIME_OF_DAY = 1000 * 60 * 60 * 24;
Calendar.DAYS_IN_MONTHLY_CALENDAR = 42;
Calendar.prototype = {
  showTable : function(element, options) {
    this.element = element;
    var calendar = this;
    if ( this.dates === null ) {
      throw new Error('arg not null');
    }
    // create table element
    var table = document.createElement('table');
    table.setAttribute("class", "calendar");

    // create caption START
    var caption = document.createElement('caption');
    var prev_element = document.createElement('a');
    prev_element.addEventListener('click', function(){ var prev = calendar.prev();prev.showTable(element);return false; });
    prev_element.setAttribute('href', '#');
    prev_element.appendChild(document.createTextNode('<'));
    caption.appendChild(prev_element);
    caption.appendChild(document.createTextNode(this.year + "/" + (this.month + 1)));
    var next_element = document.createElement('a');
    next_element.addEventListener('click', function(){ var next = calendar.next();next.showTable(element);return false; });
    next_element.setAttribute('href', '#');
    next_element.appendChild(document.createTextNode('>'));
    caption.appendChild(next_element);
    table.appendChild(caption);
    // create caption END


    // create thead START
    var thead = document.createElement('thead');
    var tr_th = document.createElement('tr');
    var week  = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
    for ( var day = 0; day < week.length; day++ ) {
      var th = document.createElement('th');
      th.appendChild(document.createTextNode(week[day]));
      th.setAttribute("class", week[day].toLowerCase());
      tr_th.appendChild(th);
    }
    thead.appendChild(tr_th)
    table.appendChild(thead);
    // create thead END

    var tbody = document.createElement('tbody');
    tbody.addEventListener('scroll', createScrollListener(this), true);
    table.appendChild(tbody);

    var tr_for_scroll = document.createElement('tr');
//    tr_for_scrolltop.style.visibility = "hidden";
    tbody.appendChild(tr_for_scroll);
    var tr_tb = document.createElement('tr');
    tbody.appendChild(tr_tb);

    var aDate = null;
    for ( var i = 0; i < this.dates.length; i++ ) {
      aDate = this.dates[i];
      var day = aDate.getDay();
      if ( i > 0 && day === 0 ) {
        tr_tb = document.createElement('tr');
        tbody.appendChild(tr_tb);
      }
      var td = document.createElement('td');
      td.appendChild(document.createTextNode(aDate.getDate()));
      if (aDate.getMonth() !== this.month ) {
        td.setAttribute("class", week[day].toLowerCase() + " sub");
      } else {
        td.setAttribute("class", week[day].toLowerCase());
      }
      td.setAttribute('year', aDate.getFullYear());
      td.setAttribute('month', aDate.getMonth() + 1);
      td.setAttribute('date', aDate.getDate());
      tr_tb.appendChild(td);
    }
    tbody.appendChild(tr_for_scroll.cloneNode());
    element.innerHTML = '';
    element.appendChild(table)

    tbody.scrollTop = tr_for_scroll.clientHeight
  },
  prev : function() {
    var prev;
    var prev_month = this.month - 1;
    if ( prev_month < 0 ) {
      prev = new Calendar(this.year - 1, 11);
    } else {
      prev = new Calendar(this.year, prev_month);
    }
    if ( this.element ) {
      prev.showTable(this.element);
    }
    return prev;
  },
  next : function() {
    var next;
    var next_month = this.month + 1;
    if ( next_month > 11) {
      next = new Calendar(this.year + 1, 0);
    } else {
      next = new Calendar(this.year, next_month);
    }
    if ( this.element ) {
      next.showTable(this.element)
    }
    return next;
  }
}

function createScrollListener( table ) {
  return function(ev) {
    if (this.scrollTop === 0 ) {
      table.changeTimer = setTimeout(function(){table.prev()}, 600);
    } else if ( this.scrollTop + this.clientHeight >= this.scrollHeight ) {
      table.changeTimer = setTimeout(function(){table.next()}, 600);
    } else {
      clearTimeout(table.changeTimer);
    }
  }
}
