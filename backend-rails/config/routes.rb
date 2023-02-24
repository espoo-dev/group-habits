Rails.application.routes.draw do
  devise_for :admin_users, ActiveAdmin::Devise.config
  ActiveAdmin.routes(self)
  ExceptionHunter.routes(self)
  mount_devise_token_auth_for 'User', at: '/api/v1/users', controllers: {
    registrations: 'api/v1/registrations',
    sessions: 'api/v1/sessions',
    passwords: 'api/v1/passwords'
  }

  namespace :api do
    namespace :v1, defaults: { format: :json } do
      resources :daily_habits, only: %i[index create]
      resources :categories, only: %i[index create destroy update]
      resources :items, only: %i[index create destroy update]
      resources :customers, only: %i[index create destroy update]
      resources :sales_units, only: %i[index]
      resources :service_orders, only: %i[index destroy]

      get :status, to: 'api#status'

      devise_scope :user do
        resource :user, only: %i[update show]
      end
      resources :settings, only: [] do
        get :must_update, on: :collection
      end
    end
  end
end
