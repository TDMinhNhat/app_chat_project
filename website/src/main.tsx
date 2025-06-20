import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {RouterProvider} from "react-router";
import router from "./routers/routers.tsx";
import {Provider} from "react-redux";
import {store} from "./stores/store.ts";
import "./styles/scss/main.scss";
import "../node_modules/bootstrap/dist/css/bootstrap.css";
import "../node_modules/bootstrap/dist/js/bootstrap.js";
import "../node_modules/jquery/dist/jquery.js";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider store={store}>
        <RouterProvider router={router} />
    </Provider>
  </StrictMode>,
)
