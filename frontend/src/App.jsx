import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import { ToastContainer } from 'react-toastify';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import RecoilNexus from 'recoil-nexus';
import MainPage from './pages/MainPage';
import AdminPage from './pages/AdminPage';
import InvoiceListPage from './pages/InvoiceListPage';
import MenuButton from './components/layout/MenuButton';
import InvoiceCreationPage from './pages/InvoiceCreationPage';
import InvoiceDetails from './pages/InvoiceDetailsPage';
import 'react-toastify/dist/ReactToastify.css';

export default function App() {
  return (
      <RecoilRoot>
        <RecoilNexus/>
        <BrowserRouter>
          <MenuButton />
          <ToastContainer />
          <Routes>
            <Route exact path="/" element={<LoginPage />} />
            <Route path="*" element={<LoginPage />} />
            <Route exact path="/register" element={<RegisterPage />} />
            <Route exact path="/main-page" element={<MainPage />} />
            <Route exact path="/admin" element={<AdminPage />} />
            <Route exact path="/invoice-list" element={<InvoiceListPage />} />
            <Route exact path="/invoice-creation" element={<InvoiceCreationPage />} />
            <Route path="/invoice/:id" element={<InvoiceDetails />} />
          </Routes>
        </BrowserRouter>
      </RecoilRoot>
  );
}
