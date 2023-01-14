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
        user_data = resource_data(resource_json: @resource.token_validation_response)
        user_data['authorization'] = @resource.generate_bearer_token(@token)
        render json: { user: user_data }
      end
    end
  end
end
