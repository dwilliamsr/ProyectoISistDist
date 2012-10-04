  function ListadoAverias() {
    d3.json("http://localhost:8089/rest/averias,function(json) {
      var content = d3.select("#content");
      content.selectAll("div").remove();
      var div = conten t.append("div");
      div.append("h2").text("Listado de averias");
      var table = div.append("table");
      //thead.append("th").text("CodAveria");
      thead.append("th").text("Descripcion");
      thead.append("th").text("Fecha");
      thead.append("th").text("Servicio_codservicio");
      var tr = table.selectAll("tr")
                  .data(json.averia)
                  .enter().append("tr")
                  .attr("onClick","DetalleAveria(this)");
      tr.attr("codaveria",function(d) {return d.codaveria;});
      tr.append("td").text(function(d) {return d.CodAveria;});
      tr.append("td").text(function(d) {return d.Descripcion;});
      tr.append("td").text(function(d) {return d.Fecha;});
      tr.append("td").text(function(d) {return d.Servicio_codservicio;});
      div.append("input").attr("type","button").attr("value","Agregar").attr("codaveria","button").attr("onClick","AgregarAveria(this)");
    });
  }
