import config from './config' // 首页
import dynamicLine from './dynamicLine/dynamicLine'
import leida from './leida'
import ringPie from './ringPie'
import colorfulRadar from './colorfulRadar'
import gauge from './gauge'
import seamless from './seamless'
//    Screen
import dynamicLine1 from './Screen/model'
import barChart from './Screen/barChart'
import lineChart from './Screen/lineChart'
import pieChart from './Screen/pieChart'
import dashboard from './Screen/dashboard'
import radarChart from './Screen/radarChart'
import list from  './Screen/list'
import waterSphereChart from './Screen/waterSphereChart'
import mapChart from './Screen/mapChart'
import configChart from './Screen/configChart'
import threeDashboards from "./Screen/threeDashboards";
import threeDashboards1 from "./Screen/threeDashboards1";
import threeDashboards2 from "./Screen/threeDashboards2";
import model from "./Screen/model";
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
    pieChart,
    dashboard,
    radarChart,
    list,
    waterSphereChart,
    mapChart,
    configChart,
    threeDashboards,
    threeDashboards1,
    threeDashboards2,
    model,
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
