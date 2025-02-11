import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Login from "./components/Login.tsx";
import Register from "./components/Register.tsx";
import BookTable from "./components/BookTable.tsx";
import PopularBooks from "./components/PopularBooks.tsx";
import BookSearch from "./components/BookSearch.tsx";
import MyPage from "./components/MyPage.tsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/login" replace/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/book" element={<BookTable />}/>
                <Route path="/popular" element={<PopularBooks/>}/>
                <Route path="/search" element={<BookSearch/>}/>
                <Route path="/mypage" element={<MyPage/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default App
