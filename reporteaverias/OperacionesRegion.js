
function DetalleRegion(row) {
    d3.json("http://localhost:8089/rest/regiones/"+row.id, function(region) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",region.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de region");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Encargado:");
tr.append("td").append("input")
.attr("value",region.encargado)
.attr("id","encargado");
tr.append("td").text("Sucursal:");
tr.append("td").append("input")
.attr("value",region.sucursal)
.attr("id","sucursal");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarRegion(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarRegion(this)");
    });
  }
  function AgregarRegion(row) {
    d3.json("http://localhost:8089/rest/regiones/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo region");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Encargado:");
tr.append("td").append("input")
.attr("value","")
.attr("id","encargado");
tr.append("td").text("Sucursal:");
tr.append("td").append("input")
.attr("value","")
.attr("id","sucursal");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarRegion(this)");
    });
  }

function EliminarRegion() {
var id  = d3.select("#id").property("value");
var encargado  = d3.select("#encargado").property("value");
var sucursal  = d3.select("#sucursal").property("value");
var params = "id="+id+"&encargado="+encargado+"&sucursal="+sucursal;
d3.xhr2("http://localhost:8089/rest/regiones/"+id,"DELETE",params,function(json) {
    });
ListadoRegiones();
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

function ActualizarRegion() {
var id  = d3.select("#id").property("value");
var encargado  = d3.select("#encargado").property("value");
var sucursal  = d3.select("#sucursal").property("value");
var params = "id="+id+"&encargado="+encargado+"&sucursal="+sucursal;
d3.xhr2("http://localhost:8089/rest/regiones/"+id,"PUT",params,function(json) {
    });
ListadoRegiones();
  }

  function AgregarRegion(row) {
    d3.json("http://localhost:8089/rest/regiones/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo region");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Encargado:");
tr.append("td").append("input")
.attr("value","")
.attr("id","encargado");
tr.append("td").text("Sucursal:");
tr.append("td").append("input")
.attr("value","")
.attr("id","sucursal");

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarRegion(this)");
    });
  }

  function ListadoRegiones() {
    d3.json("http://localhost:8089/rest/regiones",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de regiones");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Encargado");
      thead.append("th").text("Sucursal");
      var tr = table.selectAll("tr")
                  .data(json.region)
                  .enter().append("tr")
                  .attr("onClick","DetalleRegion(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.encargado;});
      tr.append("td").text(function(d) {return d.sucursal;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarRegion(this)");
    });
  }
