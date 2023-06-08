class AuthController < ApplicationController
  def reset_password; end

  private

  def auth_params
    params.permit('access-token', :client, :client_id, :token, :uid)
  end
end
