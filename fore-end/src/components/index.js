import config from './config' // 首页
import dynamicLine from './dynamicLine/dynamicLine'
import leida from './leida'
import ringPie from './ringPie'
import colorfulRadar from './colorfulRadar'
import gauge from './gauge'
import seamless from './seamless'
//    Screen
import dynamicLine1 from './Screen/resume'
import lineChart1 from './Screen/lineChart1'
import pieChart1 from './Screen/pieChart1'
import dashboard from './Screen/dashboard'
import radarChart1 from './Screen/radarChart1'
import list from  './Screen/list'
import waterSphereChart from './Screen/waterSphereChart'
import mapChart1 from './Screen/mapChart1'
import configChart from './Screen/configChart'
import threeDashboards from "./Screen/threeDashboards";
import threeDashboards1 from "./Screen/threeDashboards1";
import threeDashboards2 from "./Screen/threeDashboards2";
import resume from "./Screen/resume";
import barChart1 from "./Screen/barChart1";
//与后端连通的组件
import barChart from './Screen/back-end/barChart'
import pieChart from './Screen/back-end/pieChart'
import threePanels from "./Screen/back-end/threePanels";
import entryChart from "./Screen/back-end/entryChart";
import lineChart from "./Screen/back-end/lineChart";
import radarChart from "./Screen/back-end/radarChart";
import mapChart from "./Screen/back-end/mapChart";
//与地图连通
// import resume from "./Screen/back-end/resume";

const components = {
    config,
    dynamicLine,
    leida,
    ringPie,
    colorfulRadar,
    gauge,
    seamless,
    dynamicLine1,
    lineChart1,//纯前端
    dashboard,
    radarChart1,
    list,
    waterSphereChart,
    mapChart1,
    configChart,
    threeDashboards,
    threeDashboards1,
    threeDashboards2,
    resume,
    pieChart1,//纯前端
    barChart1,//纯前端
    //与后端连通的组件
    threePanels,
    barChart,
    pieChart,
    entryChart,
    lineChart,
    radarChart,
    mapChart,
    //与地图连通
    // resume
};

const install = (Vue = {}) => {
    if (install.installed) return;
    Object.keys(components).forEach(component => {
        Vue.component(components[component].name, components[component]);
    });

    install.installed = true;
};

install.installed = false;

if (typeof window !== "undefined" && window.Vue) {
    install(window.Vue);
    install.installed = true;
}

const Vcomp = {
    ...components,
    install
};


export default Vcomp
