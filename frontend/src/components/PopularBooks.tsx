import React, {useState} from 'react';
import {IoMenu} from "react-icons/io5";

const PopularBooks: React.FC = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    const toggleMenu = () => setMenuOpen(!menuOpen);

    return (
        <div className="min-h-screen bg-gray-100">
            <header className="bg-green-600 text-white py-4 flex justify-between items-center px-5">
                <h1 className="text-2xl font-medium text-left">図書館の人気書籍</h1>
                <div className="relative">
                    <button
                        onClick={toggleMenu}
                        className="p-2 text-white rounded-full focus:outline-none"
                    >
                        <IoMenu className="text-white mx-3 w-6 h-6"/>
                    </button>
                    {menuOpen && (
                        <div className="absolute right-0 mt-2 w-48 bg-white rounded shadow-lg z-10">
                            <ul className="text-gray-800">
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">すべての書籍</li>
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">書籍の検索</li>
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">マイページ</li>
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">ログアウト</li>
                            </ul>
                        </div>
                    )}
                </div>
            </header>
        </div>
    );
};

export default PopularBooks;
