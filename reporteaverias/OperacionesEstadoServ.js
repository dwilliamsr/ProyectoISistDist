
function DetalleEstadoserv(row) {
    d3.json("http://localhost:8089/rest/estadosserv/"+row.id, function(estadoserv) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",estadoserv.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de estadoserv");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value",estadoserv.nombre)
.attr("id","nombre");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value",estadoserv.servicio)
.attr("id","servicio");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarEstadoserv(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarEstadoserv(this)");
    });
  }
  function AgregarEstadoserv(row) {
    d3.json("http://localhost:8089/rest/estadosserv/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo estadoserv");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarEstadoserv(this)");
    });
  }

function EliminarEstadoserv() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&nombre="+nombre+"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/estadosserv/"+id,"DELETE",params,function(json) {
    });
ListadoEstadosserv();
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

function ActualizarEstadoserv() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&nombre="+nombre+"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/estadosserv/"+id,"PUT",params,function(json) {
    });
ListadoEstadosserv();
  }

  function AgregarEstadoserv(row) {
    d3.json("http://localhost:8089/rest/estadosserv/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo estadoserv");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarEstadoserv(this)");
    });
  }

  function ListadoEstadosserv() {
    d3.json("http://localhost:8089/rest/estadosserv",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de estadosserv");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Nombre");
      thead.append("th").text("Servicio");
      var tr = table.selectAll("tr")
                  .data(json.estadoserv)
                  .enter().append("tr")
                  .attr("onClick","DetalleEstadoserv(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.nombre;});
      tr.append("td").text(function(d) {return d.servicio;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarEstadoserv(this)");
    });
  }
