import {createSlice} from "@reduxjs/toolkit";
import english from "../../languages/english.json";
import vietnamese from "../../languages/vietnamese.json";

export const languageSlice = createSlice({
    name: "language",
    initialState: english,
    reducers: {
        setLanguage: (__state, action) => {
            switch(action.payload) {
                case "english":
                    return english
                case "vietnamese":
                    return vietnamese
                default:
                    return english; // Default to English if no match
            }
        }
    }
})

export const { setLanguage } = languageSlice.actions;
export default languageSlice.reducer;