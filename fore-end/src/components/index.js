import HelloWorld from './HelloWorld' // 首页
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
// <<<<<<< HEAD
import mapChart from './Screen/mapChart'
import configChart from './Screen/configChart'
import threeDashboards from "./Screen/threeDashboards";
import threeDashboards1 from "./Screen/threeDashboards1";
import threeDashboards2 from "./Screen/threeDashboards2";
// =======
// import configChart from './Screen/configChart'
// >>>>>>> 5480cf94594c569991ffa5ef624aac07c991de19
const components = {
    HelloWorld,
    dynamicLine,
    leida,
    ringPie,
    colorfulRadar,
    gauge,
    seamless,
//    Screen
    dynamicLine1,
    barChart,
    lineChart,
    pieChart,
    dashboard,
    radarChart,
    list,
    waterSphereChart,
// <<<<<<< HEAD
    mapChart,
    configChart,
    threeDashboards,
    threeDashboards1,
    threeDashboards2
// =======
//     configChart
// >>>>>>> 5480cf94594c569991ffa5ef624aac07c991de19
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
