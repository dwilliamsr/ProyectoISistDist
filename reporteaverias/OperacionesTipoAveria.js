
function DetalleTipoaveria(row) {
    d3.json("http://localhost:8089/rest/tiposaveria/"+row.id, function(tipoaveria) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("input")
        .attr("value",tipoaveria.id)
        .attr("id","id").
        attr("type","hidden");
      div.append("h2").text("Detalle de tipoaveria");
      var table = div.append("table");
      var tr = table.append("tr");
tr.append("td").text("Nombre:");
tr.append("td").append("input")
.attr("value",tipoaveria.nombre)
.attr("id","nombre");
tr.append("td").text("Averia:");
tr.append("td").append("input")
.attr("value",tipoaveria.averia)
.attr("id","averia");
      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarTipoaveria(this)");
      div.append("input").attr("type","button").attr("value","Eliminar").attr("id","button").attr("onClick","EliminarTipoaveria(this)");
    });
  }
  function AgregarTipoaveria(row) {
    d3.json("http://localhost:8089/rest/tiposaveria/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo tipoaveria");
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

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarTipoaveria(this)");
    });
  }

function EliminarTipoaveria() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var averia  = d3.select("#averia").property("value");
var params = "id="+id+"&nombre="+nombre+"&averia="+averia;
d3.xhr2("http://localhost:8089/rest/tiposaveria/"+id,"DELETE",params,function(json) {
    });
ListadoTiposaveria();
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

function ActualizarTipoaveria() {
var id  = d3.select("#id").property("value");
var nombre  = d3.select("#nombre").property("value");
var averia  = d3.select("#averia").property("value");
var params = "id="+id+"&nombre="+nombre+"&averia="+averia;
d3.xhr2("http://localhost:8089/rest/tiposaveria/"+id,"PUT",params,function(json) {
    });
ListadoTiposaveria();
  }

  function AgregarTipoaveria(row) {
    d3.json("http://localhost:8089/rest/tiposaveria/nuevo", function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
	  var idAleatorio = Math.floor(Math.random()*5000)
      var div = content.append("div");
      div.append("input")
        .attr("value",idAleatorio)
        .attr("id","id").
attr("type","hidden");
      div.append("h2").text("Nuevo tipoaveria");
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

      div.append("input").attr("type","button").attr("value","Actualizar").attr("id","button").attr("onClick","ActualizarTipoaveria(this)");
    });
  }

  function ListadoTiposaveria() {
    d3.json("http://localhost:8089/rest/tiposaveria",function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = content.append("div");
      div.append("h2").text("Listado de tiposaveria");
      var table = div.append("table");
      var thead = table.append("thead");
      thead.append("th").text("Id");
      thead.append("th").text("Nombre");
      thead.append("th").text("Averia");
      var tr = table.selectAll("tr")
                  .data(json.tipoaveria)
                  .enter().append("tr")
                  .attr("onClick","DetalleTipoaveria(this)");
      tr.attr("id",function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.id;});
      tr.append("td").text(function(d) {return d.nombre;});
      tr.append("td").text(function(d) {return d.averia;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("id","button").attr("onClick","AgregarTipoaveria(this)");
    });
  }
