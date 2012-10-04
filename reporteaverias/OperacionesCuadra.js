
function DetalleCuadra(row) {
    d3.json("http://localhost:8089/rest/cuadras/"+row.id, function(cuadra) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",cuadra.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de cuadra");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value",cuadra.nombre)
.attr("id","nombre");
tr.append("td").text("Region:");
tr.append("td").append("input")
.attr("value",cuadra.region)
.attr("id","region");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarCuadra(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarCuadra(this)");
    });
  }
  function AgregarCuadra(row) {
    d3.json("http://localhost:8089/rest/cuadras/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo cuadra");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Region:");
tr.append("td").append("input")
.attr("value","")
.attr("id","region");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarCuadra(this)");
    });
  }

function EliminarCuadra() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var region  = d3.select("#region").property("value");
var params = "id="+id+"&nombre="+nombre+"&region="+region;
d3.xhr2("http://localhost:8089/rest/cuadras/"+id,"DELETE",params,function(json) {
    });
ListadoCuadras();
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

function ActualizarCuadra() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var region  = d3.select("#region").property("value");
var params = "id="+id+"&nombre="+nombre+"&region="+region;
d3.xhr2("http://localhost:8089/rest/cuadras/"+id,"PUT",params,function(json) {
    });
ListadoCuadras();
  }

  function AgregarCuadra(row) {
    d3.json("http://localhost:8089/rest/cuadras/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo cuadra");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value","")
.attr("id","nombre");
tr.append("td").text("Region:");
tr.append("td").append("input")
.attr("value","")
.attr("id","region");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarCuadra(this)");
    });
  }

  function ListadoCuadras() {
    d3.json("http://localhost:8089/rest/cuadras",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de cuadras");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Nombre");
      thead.append("th").text("Region");
      var tr = table.selectAll("tr")
                  .data(json.cuadra)
                  .enter().append("tr")
                  .attr("onClick","DetalleCuadra(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.nombre;});
      tr.append("td").text(function(d) {return d.region;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarCuadra(this)");
    });
  }
