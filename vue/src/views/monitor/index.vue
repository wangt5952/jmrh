<template>
<div class="dashboard-container">
  <div class="martop5" style="padding:30px;border:solid 1px #e6e6e6;">
    <h3 style="margin:0">流程一览</h3>
    <div class="jtk-demo-canvas canvas-wide flowchart-demo jtk-surface jtk-surface-nopan" id="canvas" style="height:100%;list-style-type:none;">

      <div class="window jtk-node" :id="item.id" v-for="item in tableData" style="background-color:#fff;box-shadow:none;transition:none;border:none;padding:0;width:50px;height:46px;">
        <div style="color:#000">
          <img v-if="item.type=='StartEvent'" :src="writeImg">
          <img v-else-if="item.type=='EndEvent'" :src="comImg">
          <img v-else-if="item.type=='CallActivity'" :src="dataImg">
          <img v-else :src="toolsImg">
          <div style="position:relative;bottom: 15px;">
            {{item.type}}
          </div>
        </div>
        </tbody>
      </div>
    </div>
  </div>
</div>
</div>
</template>

<script>
import {
  modelAll,
  model_bsID,
  getAllFlowData,
  modelID
} from '@/api/modelDiy'
import {
  getAllBs,
  getBs
} from '@/api/service'
import {
  mapGetters
} from 'vuex'
import writeImg from '@/assets/images/weixin.png'
import toolsImg from '@/assets/images/weixin.png'
import dataImg from '@/assets/images/weixin.png'
import comImg from '@/assets/images/weixin.png'
// import greenImg from '@/assets/images/green.png'
// import redImg from '@/assets/images/red.png'
// import comImg from '@/assets/images/com.png'
//
// import ipadImg from '@/assets/images/ipad.png'
export default {
  data() {
    return {
      writeImg,
      toolsImg,
      dataImg,
      comImg,
      tableData: [],
    }
  },
  name: 'dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  mounted() {
    this.loadMDM()
  },
  methods: {
    async loadMDM() {
      let modeData = await getAllFlowData()
      modeData = modeData.data
      let mdmData = []
      let mdmFlow = []
      let index = 0
      for (var i = 0; i < modeData.length; i++) {
        modeData[i].flowChartContent = JSON.parse(modeData[i].flowChartContent)
        if (modeData[i].flowChartContent != null) {
          index++
          let elements = modeData[i].flowChartContent.elements
          let flows = modeData[i].flowChartContent.flows
          let elementsid = modeData[i].id
          for (var j = 0; j < elements.length; j++) {
            elements[j].id = elements[j].id + elementsid
            elements[j].num = index
            elements[j].indexWidth = j
            elements[j].width = elements.length
          }
          for (var k = 0; k < flows.length; k++) {
            flows[k].sourceRef = flows[k].sourceRef + elementsid
            flows[k].targetRef = flows[k].targetRef + elementsid
          }
          mdmData.push(...elements);
          mdmFlow.push(...flows);
        }

      }
      debugger
      this.tableData = mdmData //mdm表数据
      setTimeout(() => {
        this.loadjsPlumb(mdmData, mdmFlow)
      }, 10);

      // if (modeData[i].flowChartContent != null) {
    },
    async loadjsPlumb(data, flows) {
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
            radius: 1,
            lineWidth: 1
          },
          isSource: true,
          maxConnections: 200,
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
          //   gap: 5,
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
            radius: 1,
            lineWidth: 1
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
          console.log(connection)
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

      instance.batch(function() {


        for (var i = 0; i < data.length; i++) {
          let ipadr = data[i].id
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
        for (var i = 0; i < data.length; i++) {

          let ipadr = data[i].id

          // if (data[i].type == 'CallActivity' || data[i].type == 'ParallelGateway') {
            document.getElementById(ipadr).style.left = (80 / data[i].width *  data[i].indexWidth+15)  +"%";
            document.getElementById(ipadr).style.top = 15 * (data[i].num + 1) + "%"; //位置调整
          // }

          // if (data[i].type == 'StartEvent') {
          //   document.getElementById(ipadr).style.left = "16%";
          //   document.getElementById(ipadr).style.top = 15 * (data[i].num + 1) + "%"; //位置调整
          // }
          // if (data[i].type == 'EndEvent') {
          //   document.getElementById(ipadr).style.left = "76%";
          //   document.getElementById(ipadr).style.top = 15 * (data[i].num + 1) + "%"; //位置调整
          // }

          for (var j = 0; j < flows.length; j++) {
            instance.connect({
              uuids: [flows[j].sourceRef + "Right", flows[j].targetRef + "Left"],
              editable: true
            });
          }
        }


        // instance.bind("connection", function(connInfo, originalEvent) {
        //   connInfo.connection.getOverlay("label").show();
        // });
        instance.bind("mousedown", function(conn, originalEvent) {
          console.log(conn)
        })


        // make all the window divs draggable
        instance.draggable(jsPlumb.getSelector(".flowchart-demo .window"), {
          grid: [20, 20]
        });

        instance.bind("dblclick", function(conn, originalEvent) {
          // if (confirm("Delete connection from " + conn.sourceId + " to " + conn.targetId + "?"))
          //   instance.detach(conn);6


        });

        instance.bind("connectionDrag", function(connection) {
          console.log(connection)
          //   if (connection.targetId.indexOf("jsPlumb") === -1) {
          //     deleteLine(connection)
          //   }
          //   console.log("connection " + connection.id + " is being dragged. suspendedElement is ", connection.suspendedElement, " of type ", connection.suspendedElementType);
        });

        instance.bind("connectionDragStop", function(connection) {

        });

        instance.bind("connectionMoved", function(params) {
          console.log("connection " + params.connection.id + " was moved");
        });
        jsPlumb.fire("jsPlumbDemoLoaded", instance);
      });

      instance.repaintEverything();
    }
  }
}
</script>

<style lang="scss">
@import '../../styles/doabord.scss'; // 全局自定义的css样式
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
    &-container {
        margin: 30px;
    }
    &-text {
        font-size: 30px;
        line-height: 46px;
    }
}
</style>
