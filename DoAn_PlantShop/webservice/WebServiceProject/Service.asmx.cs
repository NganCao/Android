using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceProject
{
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Service : System.Web.Services.WebService
    {
        private DataClassesTreeDataContext db = new DataClassesTreeDataContext();

        // Menu

        [WebMethod(Description = "Đếm số menu")]
        public int CountMenu()
        {
            return db.Menus.Count();
        }
        [WebMethod(Description = "Liet ke menu")]
        public List<Menu> GetListMenu()
        {
            List<Menu> list = db.Menus.ToList();
            foreach (Menu m in list)
                m.SanPhams.Clear();
            return list;
            //return db.Menus.ToList();
        }
        [WebMethod(Description = "Tim menu theo id")]
        public Menu GetMenu(int maMenu)
        {
            Menu m = db.Menus.Single(dm => dm.MaMenu == maMenu);
            m.SanPhams.Clear();
            return m;
            //return db.Menus.Single(dm => dm.MaMenu == maMenu);
        }
        [WebMethod(Description = "Them menu")]
        public bool InsertMenu(string menu)
        {
            try
            {
                Menu m = new Menu();
                m.TenMenu = menu;
                db.Menus.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Xoa menu")]
        public bool DeleteMenu(int maMenu)
        {
            try
            {
                Menu m = db.Menus.Single(dm => dm.MaMenu == maMenu);
                db.Menus.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat menu")]
        public bool UpdateMenu(int maMenu, string menu)
        {
            try
            {
                Menu m = db.Menus.Single(dm => dm.MaMenu == maMenu);
                m.TenMenu = menu;
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        // San pham
        [WebMethod(Description = "Dem so san pham")]
        public int CountSanPham()
        {
            List<SanPham> list = db.SanPhams.ToList();
            return list.Count();
        }
        [WebMethod(Description = "Liet ke san pham")]
        public List<SanPham> GetListSanPham()
        {
            List<SanPham> list = db.SanPhams.ToList();
            foreach (SanPham s in list)
            {
                s.Menu = null;
                s.CT_DDHs.Clear();
            }
            return list;
            //return db.SanPhams.ToList();
        }

        [WebMethod(Description = "Liet ke san pham theo menu")]
        public List<SanPham> GetListSanPhamTheoMenu(int mamenu)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.MaMenu == mamenu).ToList();
            foreach (SanPham s in list)
            {
                s.MaMenu = null;
                s.CT_DDHs.Clear();
            }
            return list;
        }

        [WebMethod(Description = "Dem so san pham theo menu")]
        public int CountSanPhamByMenu(int mamenu)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.MaMenu == mamenu).ToList();
            return list.Count();
        }

        [WebMethod(Description = "Liet ke n san pham moi nhat")]
        public List<SanPham> GetListSanPhamMoiNhat(int n)
        {
            //List<SanPham> list = db.SanPhams.OrderByDescending(x => x.MaSP).Take(n).ToList();
            List<SanPham> list = db.SanPhams.OrderByDescending(x => x.MaSP).Take(n).ToList();
            foreach (SanPham s in list)
            {
                s.CT_DDHs.Clear();
                s.Menu = null;
            }

            return list;
        }

        [WebMethod(Description = "Sap xep san pham moi nhat")]
        public List<SanPham> FilterListSanPhamMoiNhat()
        {
            
            List<SanPham> list = db.SanPhams.OrderByDescending(x => x.MaSP).ToList();
            foreach (SanPham s in list)
            {
                s.CT_DDHs.Clear();
                s.Menu = null;
            }

            return list;
        }

        [WebMethod(Description = "Kiem tra sp co ton tai hay khong")]
        public int KiemTraSP(int masp)
        {
            SanPham s = db.SanPhams.FirstOrDefault(x => x.MaSP == masp);
            if (s == null)
            {
                return 0;
            }
            return 1;
        }

        [WebMethod(Description = "Tim san pham theo id")]
        public SanPham GetSanPham(int maSP)
        {
            
            SanPham s = db.SanPhams.Single(sp => sp.MaSP == maSP);
            
            s.Menu = null;
            s.CT_DDHs.Clear();
            return s;
            
            
             //return db.SanPhams.Single(sp => sp.MaSP == maSP);
        }

        [WebMethod(Description = "Tim san pham theo ten san pham")]
        public int GetMaSPByName(string name)
        {
            SanPham s = db.SanPhams.Single(x => x.TenSP == name);

            s.Menu = null;
            s.CT_DDHs.Clear();

            return s.MaSP;
        }

        [WebMethod(Description = "Tim kiem san pham theo ten san pham")]
        public List<SanPham> GetListSanPhamByName(string name)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.TenSP.Contains(name)).ToList();
            foreach (SanPham s in list)
            {
                s.Menu = null;
                s.CT_DDHs.Clear();
            }
            return list;
        }

        [WebMethod(Description = "3 san pham cung loai - nhap n (số sp), mamenu, masp")]
        public List<SanPham> Get3SPCungLoai(int n, int mamenu, int masp)
        {
            List<SanPham> list = db.SanPhams.Where(x => x.MaMenu == mamenu && x.MaSP != masp).ToList();
            List<SanPham> temp = new List<SanPham>();
            int i = 0;
            foreach (SanPham s in list)
            {
                if (i == n)
                {
                    break;
                }
                else
                {
                    s.Menu = null;
                    s.CT_DDHs.Clear();
                    temp.Add(s);
                    i++;
                }
            }
            return temp;
        }
        [WebMethod(Description = "Them san pham")]
        public bool InsertSanPham(string name, string img, string infor, int price, int type)
        {
            try
            {
                SanPham m = new SanPham();
                m.TenSP = name;
                m.HinhAnh = img;
                m.ThongTin = infor;
                m.Gia = price;
                m.MaMenu = type;
                db.SanPhams.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Xoa san pham")]
        public bool DeleteSanPham(int maSP)
        {
            try
            {
                SanPham m = db.SanPhams.Single(dm => dm.MaSP == maSP);
                db.SanPhams.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat san pham")]
        public bool UpdateSanPham(int maSP, string name, string img, string infor, int price, int type)
        {
            try
            {
                SanPham m = db.SanPhams.Single(dm => dm.MaSP == maSP);
                m.TenSP = name;
                m.HinhAnh = img;
                m.ThongTin = infor;
                m.Gia = price;
                m.MaMenu = type;
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }

        // Don dat hang
        [WebMethod(Description = "Liet ke ddh")]
        public List<DonDatHang> GetListDDH()
        {
            List<DonDatHang> list = db.DonDatHangs.ToList();
            foreach (DonDatHang dh in list)
            {
                dh.CT_DDHs.Clear();
            }
            return list;

            //return db.DonDatHangs.ToList();
        }

        [WebMethod(Description = "Tim ddh theo id")]
        public DonDatHang GetDDH(int maDDH)
        {
            return db.DonDatHangs.Single(d => d.MaDDH == maDDH);
        }

        [WebMethod(Description = "Tim maddh max")]
        public int GetMaDDHMax()
        {
            var result = db.DonDatHangs.Max(x => x.MaDDH);
            return result + 0;
        }

        [WebMethod(Description = "Them DDH, tra ve maddh")]
        public int GetMaDDH_InsertDDH(string tenKH, string sdt, string email, string diachi)
        {
            try
            {
                DonDatHang m = new DonDatHang();
                m.TenKhachHang = tenKH;
                m.SDT = sdt;
                m.Email = email;
                m.DiaChi = diachi;
                db.DonDatHangs.InsertOnSubmit(m);
                db.SubmitChanges();
                return m.MaDDH;
            }
            catch
            {
                return 0;
            }
        }

        [WebMethod(Description = "Them DDH")]
        public bool InsertDDH(string tenKH, string sdt, string email, string diachi)
        {
            try
            {
                DonDatHang m = new DonDatHang();
                m.TenKhachHang = tenKH;
                m.SDT = sdt;
                m.Email = email;
                m.DiaChi = diachi;
                db.DonDatHangs.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Xoa ddh")]
        public bool DeleteDDH(int maddh)
        {
            try
            {
                DonDatHang m = db.DonDatHangs.Single(ddh => ddh.MaDDH == maddh);
                db.DonDatHangs.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat ddh")]
        public bool UpdateDDH(int maddh, string tenKH, string sdt, string email, string diachi)
        {
            try
            {
                DonDatHang m = db.DonDatHangs.Single(ddh => ddh.MaDDH == maddh);
                m.TenKhachHang = tenKH;
                m.SDT = sdt;
                m.Email = email;
                m.DiaChi = diachi;
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }

        // Chi tiet DDH
        [WebMethod(Description = "Liet ke ct_ddh")]
        public List<CT_DDH> GetListCT_DDH()
        {
            List<CT_DDH> list = new List<CT_DDH> { };
            var query = from ord in db.DonDatHangs
                        from pro in db.SanPhams
                        join d in db.CT_DDHs
                            on new { ord.MaDDH, pro.MaSP } equals new { d.MaDDH, d.MaSP } into details
                        from d in details
                        select new { ord.MaDDH, pro.MaSP, d.DonGia, d.SoLuong };
           
            //List<CT_DDH> list = db.CT_DDHs.ToList();
            //foreach (CT_DDH ct in list)
            //{
            //    ct.SanPham = null;
            //    ct.DonDatHang = null;
            //}
            foreach (var item in query)
            {
                CT_DDH ct = new CT_DDH();
                ct.MaSP = item.MaSP;
                ct.MaDDH = item.MaDDH;
                ct.DonGia = item.DonGia;
                ct.SoLuong = item.SoLuong;
                list.Add(ct);
            }
            return list;
            //return db.CT_DDHs.ToList();
        }

        // lấy danh sách sản phẩm theo trong ct_ddh theo maDDH => CHƯA LÀM ĐƯỢC
        

        [WebMethod(Description = "Tim ct_ddh theo id")]
        public CT_DDH GetCT_DDH(int maDDH, int masp)
        {
            return db.CT_DDHs.FirstOrDefault(d => d.MaDDH == maDDH && d.MaSP == masp);
        }

        [WebMethod(Description = "Tim ct_ddh theo id")]
        public List<CT_DDH> GetCT_DDHByMaDDH(int maDDH)
        {
            List<CT_DDH> list = GetListCT_DDH();
            List<CT_DDH> test = new List<CT_DDH>();
            foreach (CT_DDH ct in list)
            {
                if (ct.MaDDH == maDDH)
                {
                    test.Add(ct);
                }
            }
            return test;
        }

        //DANG SAI -> SUA NHE

        //[WebMethod]
        //public SanPham getGia(int masp)
        //{
        //    SanPham sp = db.SanPhams.Single(x => x.MaSP == masp);
        //    return sp;
        //}
        [WebMethod(Description = "Them ct_ddh")]
        public bool InsertCT_DDH(int maddh, int masp, int soluong, int dongia)
        {
            try
            {

                CT_DDH m = new CT_DDH();


                m.MaDDH = maddh;
                m.MaSP = masp;
                m.SoLuong = soluong;
                m.DonGia = dongia;
                db.CT_DDHs.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;   // thêm thành công

            }
            catch
            {
                return false;
            }

        }

        [WebMethod(Description = "Xoa CT_ddh")]
        public bool DeleteCT_DDH(int maddh, int masp)
        {
            try
            {
                CT_DDH m = db.CT_DDHs.Single(ddh => ddh.MaDDH == maddh && ddh.MaSP == masp);
                db.CT_DDHs.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat ct_ddh")]
        public bool UpdateCT_DDH(int maddh, int masp, int soluong, int dongia)
        {
            try
            {
                CT_DDH m = db.CT_DDHs.Single(ddh => ddh.MaDDH == maddh && ddh.MaSP == masp);
                m.SoLuong = soluong;
                m.DonGia = dongia;
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }

        // Tai khoan
        [WebMethod(Description = "Liet ke tai khoan")]
        public List<TaiKhoan> GetListTaiKhoan()
        {
            return db.TaiKhoans.ToList();
        }

        // lấy danh sách sản phẩm theo trong ct_ddh theo maDDH => CHƯA LÀM ĐƯỢC


        [WebMethod(Description = "Tim tai khoan theo username")]
        public TaiKhoan GetTaiKhoan(string username)
        {
            TaiKhoan tk = db.TaiKhoans.Single(d => d.Username == username);

            return tk;
            //return db.TaiKhoans.Single(d => d.Username == username);
        }


        [WebMethod(Description = "Them tai khoan")]
        public bool InsertTaiKhoan(string username, string pass)
        {
            try
            {

                TaiKhoan tk = new TaiKhoan();
                tk.Username = username;
                tk.Password = pass;
                
                db.TaiKhoans.InsertOnSubmit(tk);
                db.SubmitChanges();
                return true;   // thêm thành công

            }
            catch
            {
                return false;
            }

        }

        [WebMethod(Description = "Xoa tai khoan")]
        public bool DeleteTaiKhoan(string username)
        {
            try
            {
                TaiKhoan m = db.TaiKhoans.Single(tk => tk.Username == username);
                db.TaiKhoans.DeleteOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        [WebMethod(Description = "Cap nhat tai khoan")]
        public bool UpdateTaiKhoan(string username, string pass)
        {
            try
            {
                TaiKhoan tk = db.TaiKhoans.Single(t => t.Username == username);
                tk.Username = username;
                tk.Password = pass;
                
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }

        [WebMethod(Description = "Kiem tra dang nhap: (-1: không có username, 0: không đúng pass, 1: đúng)")]
        public int KiemTraDangNhap(string user, string pass)
        {
            var a = (from p in db.TaiKhoans
                     where p.Username == user
                     select p).FirstOrDefault();
            if (a == null)
            {
                return -1;
            }else if (a.Password == pass)
            {
                return 1;
            }
            else
            {
                return 0;
            }
            
        }

        [WebMethod(Description = "Danh sach hinh cac san pham")]
        public List<HinhSanPham> GetListHinhSP()
        {
            List<HinhSanPham> list = new List<HinhSanPham>();
            var query = from sp in db.SanPhams
                        join d in db.HinhSanPhams
                            on new { sp.MaSP } equals new { d.MaSP } into details
                        from d in details
                        select new { sp.MaSP, d.MaHinh, d.LinkHinh };
            foreach (var item in query)
            {
                HinhSanPham hinhSP = new HinhSanPham();
                hinhSP.MaSP = item.MaSP;
                hinhSP.MaHinh = item.MaHinh;
                hinhSP.LinkHinh = item.LinkHinh;
                list.Add(hinhSP);
            }
            return list;
        }

        [WebMethod(Description = "Danh sach hinh sp tu masp")]
        public List<HinhSanPham> GetListHinhSPByMaSP(int masp)
        {
            List<HinhSanPham> list = GetListHinhSP();
            List<HinhSanPham> temp = new List<HinhSanPham>();
            foreach (HinhSanPham h in list)
            {
                if (h.MaSP == masp)
                {
                    temp.Add(h);
                }
            }
            return temp;
        }

        [WebMethod(Description = "Dem so hinh sp tu masp")]
        public int CountHinhSPByMaSP(int masp)
        {
            List<HinhSanPham> list = GetListHinhSPByMaSP(masp);
            return list.Count();
        }
        
    }
}
