module Api
  module V1
    class ApiController < ActionController::API
      include Api::Concerns::ActAsApiRequest
      include Pundit::Authorization
      include DeviseTokenAuth::Concerns::SetUserByToken

      before_action :authenticate_user!, except: :status
      before_action :set_paper_trail_whodunnit

      rescue_from ActiveRecord::RecordNotFound,        with: :render_not_found
      rescue_from ActiveRecord::RecordInvalid,         with: :render_record_invalid
      rescue_from ActionController::ParameterMissing,  with: :render_parameter_missing

      def status
        render json: { online: true }
      end

      def destroy_params
        params.permit(:id)
      end

      private

      def render_not_found(exception)
        logger.info { exception } # for logging
        render json: { error: exception.message }, status: :not_found
      end

      def render_record_invalid(exception)
        logger.info { exception } # for logging
        render json: { error: exception.message }, status: :bad_request
      end

      def render_parameter_missing(exception)
        logger.info { exception } # for logging
        render json: { error: exception.message }, status: :unprocessable_entity
      end
    end
  end
end
