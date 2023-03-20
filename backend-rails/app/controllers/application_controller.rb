class ApplicationController < ActionController::Base
  include Pundit::Authorization

  # Prevent CSRF attacks by raising an exception.
  protect_from_forgery with: :exception
  before_action :set_locale

  def active_admin_controller?
    is_a?(ActiveAdmin::BaseController)
  end
  def set_locale
    I18n.locale = 'pt-br'
  end
end
