
function DetalleEstadoaveria(row) {
    d3.json("http://localhost:8089/rest/estadosaveria/"+row.id, function(estadoaveria) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",estadoaveria.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de estadoaveria");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value",estadoaveria.nombre)
.attr("id","nombre");
tr.append("td").text("Averia:");
tr.append("td").append("input")
.attr("value",estadoaveria.averia)
.attr("id","averia");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarEstadoaveria(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarEstadoaveria(this)");
    });
  }
  function AgregarEstadoaveria(row) {
    d3.json("http://localhost:8089/rest/estadosaveria/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo estadoaveria");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Averia:");
tr.append("td").append("input")
.attr("value","")
.attr("id","averia");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarEstadoaveria(this)");
    });
  }

function EliminarEstadoaveria() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var averia  = d3.select("#averia").property("value");
var params = "id="+id+"&nombre="+nombre+"&averia="+averia;
d3.xhr2("http://localhost:8089/rest/estadosaveria/"+id,"DELETE",params,function(json) {
    });
ListadoEstadosaveria();
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

function ActualizarEstadoaveria() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var averia  = d3.select("#averia").property("value");
var params = "id="+id+"&nombre="+nombre+"&averia="+averia;
d3.xhr2("http://localhost:8089/rest/estadosaveria/"+id,"PUT",params,function(json) {
    });
ListadoEstadosaveria();
  }

  function AgregarEstadoaveria(row) {
    d3.json("http://localhost:8089/rest/estadosaveria/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo estadoaveria");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Averia:");
tr.append("td").append("input")
.attr("value","")
.attr("id","averia");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarEstadoaveria(this)");
    });
  }

  function ListadoEstadosaveria() {
    d3.json("http://localhost:8089/rest/estadosaveria",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de estadosaveria");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Nombre");
      thead.append("th").text("Averia");
      var tr = table.selectAll("tr")
                  .data(json.estadoaveria)
                  .enter().append("tr")
                  .attr("onClick","DetalleEstadoaveria(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.nombre;});
      tr.append("td").text(function(d) {return d.averia;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarEstadoaveria(this)");
    });
  }
