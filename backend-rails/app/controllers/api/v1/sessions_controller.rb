module Api
  module V1
    class SessionsController < DeviseTokenAuth::SessionsController
      protect_from_forgery with: :null_session
      include Api::Concerns::ActAsApiRequest

      private

      def resource_params
        params.require(:user).permit(:email, :password)
      end

      def render_create_success
        render json: {
          user: resource_data(resource_json: @resource.token_validation_response)
        }
      end
    end
  end
end
