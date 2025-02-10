import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Login from "./components/Login.tsx";
import Register from "./components/Register.tsx";
import BookTable, {Book} from "./components/BookTable.tsx";
import PopularBooks from "./components/PopularBooks.tsx";
import BookSearch from "./components/BookSearch.tsx";

function App() {
    const handleBorrowBook = (id: number) => {
        alert(`Book ID ${id} を貸し出しました！`);
    };

    const mockBooks: Book[] = [
        {id: 1, title: 'Goプログラミング入門', status: 'AVAILABLE'},
        {id: 2, title: 'JavaScript徹底攻略', status: 'BORROWED'},
        {id: 3, title: 'React実践ガイド', status: 'AVAILABLE'},
        {id: 4, title: 'TypeScript超入門', status: 'BORROWED'},
        {id: 5, title: 'Docker入門', status: 'AVAILABLE'},
    ];

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/popular" replace/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/book" element={<BookTable books={mockBooks} borrowBook={handleBorrowBook}/>}/>
                <Route path="/popular" element={<PopularBooks/>}/>
                <Route path="/search" element={<BookSearch/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default App
