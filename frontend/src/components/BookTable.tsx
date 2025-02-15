import React, {useEffect, useState} from 'react';
import {IoMenu} from "react-icons/io5";
import {Link} from "react-router-dom";
import {MdMenuBook} from "react-icons/md";
import {useLogout} from "../utils/Logout.ts";
import {BASE_URL} from "../utils/Constants.ts";

export type Book = {
    id: number;
    title: string;
    status: 'AVAILABLE' | 'BORROWED';
};

const BookTable: React.FC = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    const toggleMenu = () => setMenuOpen(!menuOpen);
    const [books, setBooks] = useState<Book[]>([]);
    const logout = useLogout();

    useEffect(() => {
        fetchBooks()
    }, [])

    const fetchBooks = () => {
        fetch(`${BASE_URL}/book`, {
            method: "GET",
            credentials: "include",
        })
            .then((res) => {
                if (!res.ok) {
                    throw new Error(`HTTP error! status: ${res.status}`);
                }
                return res.json();
            })
            .then((data: Book[]) => setBooks(data))
            .catch((error) => {
                console.error("Error fetching books:", error);
            });
    };

    const handleBorrowBook = (id: number) => {
        alert(`Book ID ${id} を貸し出しました！`);
    };

    return (
        <div className="min-h-screen bg-gray-100">
            <header className="bg-green-600 text-white py-4 flex justify-between items-center px-5">
                <div className="flex items-center">
                    <MdMenuBook className="text-white w-8 h-8 mr-2"/>
                    <h1 className="text-2xl font-medium text-left">図書館の書籍一覧</h1>
                </div>
                <div className="relative">
                    <button
                        onClick={toggleMenu}
                        className="p-2 text-white rounded-full focus:outline-none"
                    >
                        <IoMenu className="text-white mx-3 w-7 h-7"/>
                    </button>
                    {menuOpen && (
                        <div className="absolute right-0 mt-2 w-48 bg-white rounded shadow-lg z-10">
                            <ul className="text-gray-800">
                                <Link to="/popular">
                                    <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">人気の書籍</li>
                                </Link>
                                <Link to="/search">
                                    <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">書籍の検索</li>
                                </Link>
                                <Link to="/mypage">
                                    <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">マイページ</li>
                                </Link>
                                <li
                                    className="hover:bg-gray-100 px-5 py-4 cursor-pointer"
                                    onClick={logout}>
                                    ログアウト
                                </li>
                            </ul>
                        </div>
                    )}
                </div>
            </header>
            <div className="px-20 py-6">
                <table className="table-auto border-collapse border border-gray-800 w-full">
                    <thead>
                    <tr className="bg-gray-200">
                        <th className="border border-gray-300 px-2 py-3">ID</th>
                        <th className="border border-gray-300 px-4 py-3">タイトル</th>
                        <th className="border border-gray-300 px-4 py-3">状態</th>
                        <th className="border border-gray-300 px-4 py-3">貸出</th>
                    </tr>
                    </thead>
                    <tbody>
                    {books.map((book) => (
                        <tr key={book.id}>
                            <td className="border border-gray-300 px-2 py-2 text-center">{book.id}</td>
                            <td className="border border-gray-300 px-4 py-2 text-center">{book.title}</td>
                            <td className="border border-gray-300 px-4 py-2 text-center">
                  <span className={`font-bold ${book.status === 'AVAILABLE' ? 'text-green-600' : 'text-red-600'}`}>
                    {book.status === 'AVAILABLE' ? '利用可能' : '貸出中'}
                  </span>
                            </td>
                            <td className="border border-gray-300 px-4 py-2 text-center">
                                <button
                                    className={`px-4 py-2 rounded text-white ${
                                        book.status === 'AVAILABLE'
                                            ? 'bg-green-500 hover:bg-green-600'
                                            : 'bg-gray-400 cursor-not-allowed'
                                    }`}
                                    onClick={() => handleBorrowBook(book.id)}
                                    disabled={book.status !== 'AVAILABLE'}
                                >
                                    貸出
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default BookTable;