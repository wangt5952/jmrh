<template>
<div class="tab-container bordera mara25">
  <div class="jtk-demo-canvas canvas-wide flowchart-demo jtk-surface jtk-surface-nopan" id="canvas" style="height:100%;list-style-type:none;">

    <div class="borderb ">
      <div class="paddingb textl paddingr paddingl10 paddingt5">
        MDM模块
        <el-select v-model="sysDatavalue" placeholder="请选择" @change="clean">
          <el-option v-for="item in sysData" :key="item.id" :label="item.mdmModel" :value="item.id">
          </el-option>
        </el-select>
        业务系统
        <el-select v-model="serDatavalue" placeholder="请选择" @change="clean">
          <el-option v-for="item in serData" :key="item.id" :label="item.bsName" :value="item.id">
          </el-option>
        </el-select>
        <el-button style="margin-left:20px" @click="loadMDM" type="primary" >获取</el-button>
      </div>
    </div>
    <div class="paddingb textl paddingr paddingl10 paddingt5" style="position: absolute;left: 23%;">

    </div>
    <div class="paddingb textl paddingr paddingl10 paddingt5" style="position: absolute;left: 70%;">

    </div>
    <div class="borderl h100 positiona" style="left:50%">
    </div>
    <div class="window" :id="item.id" v-for="item in tableData">
      <p class="borderb " style="margin:0;color: #fff;">{{item.tableName}}</p>
      <table class="imagetable" style="width:100%;font-size:12px;overflow-y:auto">
        <thead>
          <tr>
            <th>字段</th>
            <th>字段名称</th>
            <th>数据类型</th>
            <th>数据长度(精度)</th>
          </tr>
        </thead>
        <tbody>
          <tr class="jtk-node" :id="sParamFieldDefineds.id" v-for="sParamFieldDefineds in item.sParamFieldDefineds">
            <td class="t-middle">{{sParamFieldDefineds.fieldName}}</td>
            <td class="t-middle">主键</td>
            <td class="t-middle">{{sParamFieldDefineds.fieldType}}</td>
            <td class="t-middle">PRIMARY KEY</td>
          </tr>
          <!-- <tr>
          <td class="t-middle">columnComment</td>
          <td class="t-middle">描述</td>
          <td class="t-middle">VARCHAR2</td>
          <td class="t-middle">NOT NULL</td>
        </tr>
        <tr>
          <td class="t-middle">dataTypeMap</td>
          <td class="t-middle">数据类型</td>
          <td class="t-middle">FLOAT</td>
          <td class="t-middle">NOT NULL</td>
        </tr>
        <tr>
          <td class="t-middle">constraints</td>
          <td class="t-middle">约束</td>
          <td class="t-middle"></td>
          <td class="t-middle">default 1</td>
          </td>
        </tr> -->
        </tbody>
      </table>
    </div>
  </div>
</div>
</template>

<script>
import {
  modelAll,
  modelID,
} from '@/api/modelDiy'
import {
model_bsID,
model_dataMap,
delmodel_dataMap
} from '@/api/mapping'
import {
  getAllBs,
  getBs
} from '@/api/service'
import $ from 'jquery'
import MenuUtils from '@/views/MenuUtils'
var routers = []
export default {
  data() {
    return {
      serDatavalue: '',
      sysDatavalue: '',
      sysData: [],
      serData: [],
      mdmData: [],
      tableData: [],
      getData: false
    }
  },
  created() {},
  async mounted() {
    this.loadSys()
  },
  methods: {
    login(data) {
      window.sessionStorage.setItem('user', JSON.stringify(data))
      MenuUtils(routers, data)

    },
    clean() {
      $("#canvas path").remove()
      $("#canvas circle").remove()
      this.tableData = []
      this.getData = false
    },
    async loadMDM() {

      if (this.sysDatavalue == "") {
        this.$message({
          message: '请选择业务模块',
          type: 'success'
        });
        return
      }
      if (this.serDatavalue == "") {
        this.$message({
          message: '请选择系统模块',
          type: 'success'
        });
        return
      }

      this.getData = true
      let val = '648C7F180F35DD05E05013AC06882D5A'
      let val2 = '66F49B32E0229EFFE05013AC0688029C'
      let mdmData = []
      let modeData = await model_bsID(this.sysDatavalue, this.serDatavalue)
      if (modeData.data != null) {
        let mdmTable = modeData.data.mdmTable
        for (var a = 0; a < mdmTable.length; a++) {
          mdmTable[a].sParamFieldDefineds = mdmTable[a].columnDefinitions
          let tableData = mdmTable[a].sParamFieldDefineds
          for (var i = 0; i < tableData.length; i++) {
            tableData[i].id = tableData[i].name
            tableData[i].fieldName = tableData[i].name
            tableData[i].fieldType = tableData[i].dataTypeMap
          }
          mdmTable[a].type = 'mdm'
        }
        if (modeData.data.bsTable != null) {
          modeData.data.bsTable.type = 'yw'
          mdmTable.push(modeData.data.bsTable)
        }
        this.tableData = mdmTable //mdm表数据
        setTimeout(() => {
          this.loadjsPlumb(this.tableData)
        }, 10);
      } else {
        this.$message({
          message: modeData.message,
          type: 'warning'
        });
        return
      }

    },
    async loadSys() {
      let sysData = await modelAll()
      this.sysData = sysData.data
      let serData = await getAllBs()
      this.serData = serData.data

    },
    async loaddataMapData(sourceId, targetId, loadVueData) {
      let arr = [{
        "mdmTableId": loadVueData.mdmTableId,
        "bsTableId": loadVueData.bsTableId,
        "lineData": [{
          "sourceId": sourceId,
          "targetId": targetId
        }]
      }]
      let dataMapData = await model_dataMap(arr)
      if (dataMapData.success) {
        this.$message({
          type: 'success',
          message: '连线成功'
        });
      } else {
        this.$message({
          type: 'danger',
          message: dataMapData.message
        });
      }
    },
    async loaddelmodel_dataMap(sourceId, targetId, loadVueData,instance,conn) {
      this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        let arr = [{
          "mdmTableId": loadVueData.mdmTableId,
          "bsTableId": loadVueData.bsTableId,
          "lineData": [{
            "sourceId": sourceId,
            "targetId": targetId
          }]
        }]
        let dataMapData = await delmodel_dataMap(arr)
        if (dataMapData.success) {
          instance.deleteConnection(conn)
          this.$message({
            type: 'success',
            message: '删除成功'
          });
        } else {
          this.$message({
            type: 'danger',
            message: dataMapData.message
          });
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });




    },
    async loadjsPlumb(data) {
      let loadVueData = this
      var instance = window.jsp = jsPlumb.getInstance({
        // default drag options
        DragOptions: {
          cursor: 'pointer',
          zIndex: 2000
        },
        // the overlays to decorate each connection with.  note that the label overlay uses a function to generate the label text; in this
        // case it returns the 'labelText' member that we set on each connection in the 'init' method below.
        ConnectionOverlays: [
          ["Arrow", {
            location: 1
          }],
          // ["Label", {
          //   location: 0.35, //线上标号的位置
          //   id: "label",
          //   cssClass: "aLabel"
          // }]
        ],
        Container: "canvas"
      });
      instance.repaintEverything();
      jsPlumb.repaintEverything();
      var mos = document.querySelectorAll(".jtk-endpoint");
      for (var i = 0; i < mos.length; i++) {
        var _p = mos[i].parentNode;
        if (_p) {
          _p.removeChild(mos[i]);
        }
      }
      // var mos = document.querySelectorAll(".jsplumb-endpoint.jsplumb-endpoint-anchor,.jsplumb-overlay");
      // for (var i = 0; i < mos.length; i++) {
      //   var _p = mos[i].parentNode;
      //   if (_p) {
      //     _p.removeChild(mos[i]);
      //   }
      // }
      // var basicType = {
      //   connector: "StateMachine",
      //   paintStyle: {
      //     stroke: "black" /*, lineWidth: 1*/
      //   },
      //   hoverPaintStyle: {
      //     stroke: "black"
      //   },
      //   overlays: [
      //     "Arrow"
      //   ]
      // };
      // instance.registerConnectionType("basic", basicType);

      // this is the paint style for the connecting lines..
      var connectorPaintStyle = {
          lineWidth: 1,
          stroke: "#000", //绿色
          joinstyle: "round",
          outlineColor: "#000",
          outlineWidth: 2,
          dashstyle: "3 4", //分段显示4是每段长度 1是段与段之间的长度
        },
        // .. and this is the hover style.
        connectorHoverStyle = {
          lineWidth: 1,
          stroke: "#2cd440", //绿色
          joinstyle: "round",
          outlineColor: "#2cd440",
          outlineWidth: 2,
          dashstyle: "3 4", //分段显示4是每段长度 1是段与段之间的长度
        },
        endpointHoverStyle = {
          fillStyle: "#216477",
          stroke: "#216477"
        },

        sourceEndpoint = {
          endpoint: "Dot",
          paintStyle: {
            stroke: "#000",
            stroke: "#000",
            strokeWidth: 1,
            fillStyle: "#fff",
            radius: 10,
            lineWidth: 5
          },
          isSource: true,
          maxConnections: 1,
          connectorStyle: connectorPaintStyle, //连线样式
          hoverPaintStyle: endpointHoverStyle,
          connectorHoverStyle: connectorHoverStyle,
          dragOptions: {},
          connector: ["Bezier", {
            curviness: 63
          }],
          dragOptions: {},
          // overlays: [
          //   ["Label", {
          //     location: [0.5, 1.5],
          //     label: "",
          //     cssClass: "endpointSourceLabel"
          //   }]
          // ]
          // connector: ["Bezier", {
          //   stub: [40, 60],
          //   gap: 10,
          //   cornerRadius: 5,
          //   alwaysRespectStubs: true
          // }],
          // overlays: [
          //     ["Label", {
          //         location: [0.5, 1.5],
          //         label: "Drag",
          //         cssClass: "endpointSourceLabel"
          //     }]
          // ]
        },

        targetEndpoint = { //绿色
          endpoint: "Dot",
          paintStyle: {
            stroke: "#000",
            stroke: "#000",
            strokeWidth: 1,
            fillStyle: "#fff",
            radius: 10,
            lineWidth: 5
          },
          connector: ["Flowchart", {
            stub: [40, 60],
            gap: 10,
            cornerRadius: 1,
            alwaysRespectStubs: true
          }],
          maxConnections: 200,
          dropOptions: {
            hoverClass: "hover",
            activeClass: "active"
          },
          isTarget: true,
          // overlays: [
          //   ["Label", {
          //     location: [0.5, -0.5],
          //     label: "",
          //     cssClass: "endpointTargetLabel"
          //   }]
          // ]
        },
        init = function(connection) {
          // console.log(connection)
          // connection.getOverlay("label").setLabel(connection.sourceId + "-->" + connection.targetId);
        };

      var _addEndpoints = function(toId, sourceAnchors, targetAnchors) {
        for (var i = 0; i < sourceAnchors.length; i++) {
          var sourceUUID = toId + sourceAnchors[i];
          //  sourceEndpoint.connectorStyle.stroke = "blue";  //改变线的颜色 。
          instance.addEndpoint(toId, sourceEndpoint, {
            anchor: sourceAnchors[i],
            uuid: sourceUUID
          });
        }
        for (var j = 0; j < targetAnchors.length; j++) {
          var targetUUID = toId + targetAnchors[j];
          instance.addEndpoint(toId, targetEndpoint, {
            anchor: targetAnchors[j],
            uuid: targetUUID
          });
        }
      };




      for (var i = 0; i < data.length; i++) {
        var sParamFieldDefineds = data[i].sParamFieldDefineds
        for (var j = 0; j < sParamFieldDefineds.length; j++) {
          let ipadr = sParamFieldDefineds[j].id
          _addEndpoints(ipadr, ["Right"], ["Left"])
          instance.bind("connection", function(connInfo, originalEvent) {
            init(connInfo.connection); //   /*初始化连线标题*/
            connInfo.connection.setPaintStyle({
              lineWidth: 1,
              stroke: "#07861b", //绿色
              joinstyle: "round",
              outlineColor: "#07861b",
              outlineWidth: 2,
            });
          });
        }
      }
      for (var i = 0; i < data.length; i++) {
        let ipadr = data[i].id
        if (data[i].type == 'yw') {
          document.getElementById(ipadr).style.left = "63%";
          document.getElementById(ipadr).style.top = "25%"; //位置调整
            this.bsTableId = data[i].id
        }

        if (data[i].type == 'mdm') {
        this.mdmTableId = data[i].id
          document.getElementById(ipadr).style.left = "16%";
          if (data[i].isMenu) {
            document.getElementById(ipadr).style.top = "35%"; //位置调整
          } else {
            document.getElementById(ipadr).style.top = "60%"; //位置调整
          }
        }

        var sParamFieldDefineds = data[i].sParamFieldDefineds

        for (var j = 0; j < sParamFieldDefineds.length; j++) {
          if (sParamFieldDefineds[j].targetId != undefined && sParamFieldDefineds[j].targetId) {
            instance.connect({
              uuids: [sParamFieldDefineds[j].id + "Right", sParamFieldDefineds[j].targetId + "Left"],
              editable: true
            });
          }
        }
      }


      // instance.bind("connection", function(connInfo, originalEvent) {
      //   connInfo.connection.getOverlay("label").show();
      // });
      instance.bind("mousedown", function(conn, originalEvent) {

        // jsPlumb.detach(conn, {
        //     fireEvent: false, //fire a connection detached event?
        //     forceDetach: false //override any beforeDetach listeners
        // })
        // jsPlumb.detach(cons);
        // let cons = instance.getConnections();
        // jsPlumb.detachAllConnections(conn.sourceId);
        loadVueData.loaddelmodel_dataMap(conn.sourceId, conn.targetId, loadVueData,instance,conn)

      })


      // make all the window divs draggable
      instance.draggable(jsPlumb.getSelector(".flowchart-demo .window"), {
        grid: [20, 20]
      });

      instance.bind("dblclick", function(conn, originalEvent) {
        // if (confirm("Delete connection from " + conn.sourceId + " to " + conn.targetId + "?"))
        //   instance.detach(conn);6
        console.log(this)


      });

      instance.bind("connectionDrag", function(connection) {
        // console.log(connection)
        console.log("connectionDrag")
        //   if (connection.targetId.indexOf("jsPlumb") === -1) {
        //     deleteLine(connection)
        //   }
        //   console.log("connection " + connection.id + " is being dragged. suspendedElement is ", connection.suspendedElement, " of type ", connection.suspendedElementType);
      });
      instance.bind("connectionDragStop", function(connection) {
        if (connection.targetId.indexOf('jsPlumb') > -1) {
          loadVueData.$message({
            type: 'info',
            message: '已取消连线'
          });
          return
        }
        loadVueData.loaddataMapData(connection.sourceId, connection.targetId, loadVueData)
      });

      instance.bind("connectionMoved", function(params) {
        // console.log("connection " + params.connection.id + " was moved");
        console.log("connectionMoved")
      });
      jsPlumb.fire("jsPlumbDemoLoaded", instance);


      instance.repaintEverything();
    }
  },
  components: {}
}
</script>

<style scoped lang="scss">
.imagetable {
    font-family: verdana,arial,sans-serif;
    font-size: 11px;
    color: #878d99;
    border-width: 1px;
    border-color: #000;
    border-collapse: collapse;
}
.imagetable th {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #e6ebf5;
}
.imagetable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #e6ebf5;
}
.el-input--suffix .el-input__inner {
    height: 30px;
}
.tab-container {
    padding: 0;
}
</style>
