import {type JSX} from 'react';
import {useDispatch, useSelector} from "react-redux";
import type {RootState} from "../stores/store.ts";
import { setLanguage } from "../stores/slices/languageSlice.ts";

const HomePage = (): JSX.Element => {

    const language: object = useSelector((state: RootState): object => state.language.home_page)
    const dispatch = useDispatch();

    const handleChangeLanguage = (type: string) => {
        dispatch(setLanguage(type));
    }

    return (
        <h1>Home Page</h1>
    )
}

export default HomePage;