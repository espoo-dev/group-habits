require 'json'
module Api
  module V1
    class SocialAuthController < ApplicationController
      def authenticate_social_auth_user
        @user = User.signin_or_create_from_provider(params)
        if @user.persisted?
          sign_in(@user)

          login_token = @user.create_new_auth_token
          render json: {
            status: 'SUCCESS',
            message: "user was successfully logged in through #{provider_params}",
            headers: login_token
          }, status: :created
        else
          render json: {
            status: 'FAILURE',
            message: "There was a problem signing you in through #{provider_params}",
            data: @user.errors
          }, status: :unprocessable_entity
        end
      end

      private

      def provider_params
        params[:provider]
      end
    end
  end
end
