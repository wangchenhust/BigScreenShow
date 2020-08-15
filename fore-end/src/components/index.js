import config from './config' // 首页
import dynamicLine from './dynamicLine/dynamicLine'
import leida from './leida'
import ringPie from './ringPie'
import colorfulRadar from './colorfulRadar'
import gauge from './gauge'
import seamless from './seamless'
//    Screen
import dynamicLine1 from './Screen/resume'
import barChart from './Screen/barChart'
import lineChart from './Screen/lineChart'
import pieChart from './Screen/pieChart'
import pieChart1 from './Screen/pieChart1'
import dashboard from './Screen/dashboard'
import radarChart from './Screen/radarChart'
import list from  './Screen/list'
import waterSphereChart from './Screen/waterSphereChart'
import mapChart from './Screen/mapChart'
import configChart from './Screen/configChart'
import threeDashboards from "./Screen/threeDashboards";
import threeDashboards1 from "./Screen/threeDashboards1";
import threeDashboards2 from "./Screen/threeDashboards2";
import resume from "./Screen/resume";
import barChart1 from "./Screen/barChart1";
const components = {
    config,
    dynamicLine,
    leida,
    ringPie,
    colorfulRadar,
    gauge,
    seamless,
    dynamicLine1,
    barChart,
    lineChart,
    pieChart,//加了后端
    dashboard,
    radarChart,
    list,
    waterSphereChart,
    mapChart,
    configChart,
    threeDashboards,
    threeDashboards1,
    threeDashboards2,
    resume,
    pieChart1,//纯前端
    barChart1,//纯前端
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
