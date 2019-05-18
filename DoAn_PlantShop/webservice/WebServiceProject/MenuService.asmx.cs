using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceProject
{
    /// <summary>
    /// Summary description for MenuService
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class MenuService : System.Web.Services.WebService
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
    }
}
