
function DetalleInstalacion(row) {
    d3.json("http://localhost:8089/rest/instalaciones/"+row.id, function(instalacion) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",instalacion.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de instalacion");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("NumConcent:");
tr.append("td").append("input")
.attr("value",instalacion.numconcent)
.attr("id","numconcent");
tr.append("td").text("CantMetCable:");
tr.append("td").append("input")
.attr("value",instalacion.cantmetcable)
.attr("id","cantmetcable");
tr.append("td").text("InsPropia:");
tr.append("td").append("input")
.attr("value",instalacion.inspropia)
.attr("id","inspropia");
tr.append("td").text("CantTel:");
tr.append("td").append("input")
.attr("value",instalacion.canttel)
.attr("id","canttel");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarInstalacion(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarInstalacion(this)");
    });
  }
  function AgregarInstalacion(row) {
    d3.json("http://localhost:8089/rest/instalaciones/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo instalacion");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("NumConcent:");
tr.append("td").append("input")
.attr("value","")
.attr("id","numconcent");
tr.append("td").text("CantMetCable:");
tr.append("td").append("input")
.attr("value","")
.attr("id","cantmetcable");
tr.append("td").text("InsPropia:");
tr.append("td").append("input")
.attr("value","")
.attr("id","inspropia");
tr.append("td").text("CantTel:");
tr.append("td").append("input")
.attr("value","")
.attr("id","canttel");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarInstalacion(this)");
    });
  }

function EliminarInstalacion() {
var id  = d3.select("#id").property("value");
var numconcent  = d3.select("#numconcent").property("value");
var cantmetcable  = d3.select("#cantmetcable").property("value");
var inspropia  = d3.select("#inspropia").property("value");
var canttel  = d3.select("#canttel").property("value");
var params = "id="+id+"&numconcent="+numconcent+"&cantmetcable="+cantmetcable+"&inspropia="+inspropia+"&canttel="+canttel;
d3.xhr2("http://localhost:8089/rest/instalaciones/"+id,"DELETE",params,function(json) {
    });
ListadoInstalaciones();
  }

d3.xhr2 = function(url, method, params, mime, callback) {
    var req = new XMLHttpRequest;
    if (arguments.length < 5) callback = mime, mime = null;
    else if (mime && req.overrideMimeType) req.overrideMimeType(mime);
    req.open(method, url, true);
    if (mime) req.setRequestHeader("Accept", mime);
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.setRequestHeader("Content-length", params.length);
    req.setRequestHeader("Connection", "close");
    req.onreadystatechange = function() {
      if (req.readyState === 4) {
        var s = req.status;
        callback(s >= 200 && s < 300 || s === 304 ? req : null);
      }
    };
    req.send(params);
  };

function ActualizarInstalacion() {
var id  = d3.select("#id").property("value");
var numconcent  = d3.select("#numconcent").property("value");
var cantmetcable  = d3.select("#cantmetcable").property("value");
var inspropia  = d3.select("#inspropia").property("value");
var canttel  = d3.select("#canttel").property("value");
var params = "id="+id+"&numconcent="+numconcent+"&cantmetcable="+cantmetcable+"&inspropia="+inspropia+"&canttel="+canttel;
d3.xhr2("http://localhost:8089/rest/instalaciones/"+id,"PUT",params,function(json) {
    });
ListadoInstalaciones();
  }

  function AgregarInstalacion(row) {
    d3.json("http://localhost:8089/rest/instalaciones/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo instalacion");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("NumConcent:");
tr.append("td").append("input")
.attr("value","")
.attr("id","numconcent");
tr.append("td").text("CantMetCable:");
tr.append("td").append("input")
.attr("value","")
.attr("id","cantmetcable");
tr.append("td").text("InsPropia:");
tr.append("td").append("input")
.attr("value","")
.attr("id","inspropia");
tr.append("td").text("CantTel:");
tr.append("td").append("input")
.attr("value","")
.attr("id","canttel");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarInstalacion(this)");
    });
  }

  function ListadoInstalaciones() {
    d3.json("http://localhost:8089/rest/instalaciones",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de instalaciones");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("NumConcent");
      thead.append("th").text("CantMetCable");
      thead.append("th").text("InsPropia");
      thead.append("th").text("CantTel");
      var tr = table.selectAll("tr")
                  .data(json.instalacion)
                  .enter().append("tr")
                  .attr("onClick","DetalleInstalacion(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.numconcent;});
      tr.append("td").text(function(d) {return d.cantmetcable;});
      tr.append("td").text(function(d) {return d.inspropia;});
      tr.append("td").text(function(d) {return d.canttel;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarInstalacion(this)");
    });
  }
