import { PreloadAllModules, RouterModule, Routes } from '@angular/router'
import { NgModule } from '@angular/core'

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'products',
    pathMatch: 'full',
  },
  {
    path: 'me',
    loadComponent: () => import('./pages/me/me.page').then((m) => m.MePage),
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./pages/login/login.page').then((m) => m.LoginPage),
  },
  {
    path: 'products',
    loadComponent: () =>
      import('./pages/products/products.page').then((m) => m.ProductsPage),
  },
  {
    path: 'restaurants',
    loadComponent: () =>
      import('./pages/restaurants/restaurants.page').then(
        (m) => m.RestaurantsPage,
      ),
  },
  {
    path: 'products/new',
    loadComponent: () =>
      import('./pages/products/new/new.page').then((m) => m.NewPage),
  },
  {
    path: 'restaurants/new',
    loadComponent: () =>
      import('./pages/restaurants/new/new.page').then((m) => m.NewPage),
  },
  {
    path: 'categories',
    loadComponent: () =>
      import('./pages/categories/categories.page').then(
        (m) => m.CategoriesPage,
      ),
  },
  {
    path: 'categories/new',
    loadComponent: () =>
      import('./pages/categories/new/new.page').then((m) => m.NewPage),
  },
  {
    path: 'evaluations',
    loadComponent: () =>
      import('./pages/evaluations/evaluations.page').then(
        (m) => m.EvaluationsPage,
      ),
  },
  {
    path: 'evaluations/new',
    loadComponent: () =>
      import('./pages/evaluations/new/new.page').then((m) => m.NewPage),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./pages/register/register.page').then((m) => m.RegisterPage),
  },
  {
    path: 'categories/:id',
    loadComponent: () =>
      import('./pages/categories/update/update.page').then((m) => m.UpdatePage),
  },
  {
    path: 'evaluations/:id',
    loadComponent: () =>
      import('./pages/evaluations/update/update.page').then(
        (m) => m.UpdatePage,
      ),
  },
  {
    path: 'restaurants/:id',
    loadComponent: () =>
      import('./pages/restaurants/update/update.page').then(
        (m) => m.UpdatePage,
      ),
  },
  {
    path: 'products/:id',
    loadComponent: () =>
      import('./pages/products/update/update.page').then((m) => m.UpdatePage),
  },
  {
    path: 'products/patchImage/:id',
    loadComponent: () =>
      import('./pages/products/update-image/update-image.page').then(
        (m) => m.UpdateImagePage,
      ),
  },
  {
    path: 'orders',
    loadComponent: () =>
      import('./pages/orders/orders.page').then((m) => m.OrdersPage),
  },
  {
    path: 'offers',
    loadComponent: () =>
      import('./pages/offers/offers.page').then((m) => m.OffersPage),
  },
  {
    path: 'offers/new',
    loadComponent: () =>
      import('./pages/offers/new/new.page').then((m) => m.NewPage),
  },
  {
    path: 'offers/:id',
    loadComponent: () =>
      import('./pages/offers/update/update.page').then((m) => m.UpdatePage),
  },
  {
    path: 'update-image',
    loadComponent: () =>
      import('./pages/products/update-image/update-image.page').then(
        (m) => m.UpdateImagePage,
      ),
  },
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
