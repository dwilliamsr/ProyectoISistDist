
function DetalleAveria(row) {
    d3.json("http://localhost:8089/rest/averias/"+row.id, function(averia) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",averia.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de averia");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Descripcion:");
tr.append("td").append("input")
.attr("value",averia.descripcion)
.attr("id","descripcion");
tr.append("td").text("Fecha:");
tr.append("td").append("input")
.attr("value",averia.fecha)
.attr("id","fecha");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value",averia.servicio)
.attr("id","servicio");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarAveria(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarAveria(this)");
    });
  }
  function AgregarAveria(row) {
    d3.json("http://localhost:8089/rest/averias/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo averia");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Descripcion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","descripcion");
tr.append("td").text("Fecha:");
tr.append("td").append("input")
.attr("value","")
.attr("id","fecha");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarAveria(this)");
    });
  }

function EliminarAveria() {
var id  = d3.select("#id").property("value");
var descripcion  = d3.select("#descripcion").property("value");
var fecha  = d3.select("#fecha").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&descripcion="+descripcion+"&fecha="+fecha+"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/averias/"+id,"DELETE",params,function(json) {
    });
ListadoAverias();
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

function ActualizarAveria() {
var id  = d3.select("#id").property("value");
var descripcion  = d3.select("#descripcion").property("value");
var fecha  = d3.select("#fecha").property("value");
var servicio  = d3.select("#servicio").property("value");
var params = "id="+id+"&descripcion="+descripcion+"&fecha="+fecha+"&servicio="+servicio;
d3.xhr2("http://localhost:8089/rest/averias/"+id,"PUT",params,function(json) {
    });
ListadoAverias();
  }

  function AgregarAveria(row) {
    d3.json("http://localhost:8089/rest/averias/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo averia");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Descripcion:");
tr.append("td").append("input")
.attr("value","")
.attr("id","descripcion");
tr.append("td").text("Fecha:");
tr.append("td").append("input")
.attr("value","")
.attr("id","fecha");
tr.append("td").text("Servicio:");
tr.append("td").append("input")
.attr("value","")
.attr("id","servicio");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarAveria(this)");
    });
  }

  function ListadoAverias() {
    d3.json("http://localhost:8089/rest/averias",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de averias");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Descripcion");
      thead.append("th").text("Fecha");
      thead.append("th").text("Servicio");
      var tr = table.selectAll("tr")
                  .data(json.averia)
                  .enter().append("tr")
                  .attr("onClick","DetalleAveria(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.descripcion;});
      tr.append("td").text(function(d) {return d.fecha;});
      tr.append("td").text(function(d) {return d.servicio;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarAveria(this)");
    });
  }
