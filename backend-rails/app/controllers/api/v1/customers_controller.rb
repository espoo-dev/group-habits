module Api
  module V1
    class CustomersController < Api::V1::ApiController
      def index
        items = CustomersFinderService.new(user: current_user, index_params:).call
        render json: CustomerPresenter.payload_for_list(items)
      end

      private

      def index_params
        params.permit(:name, :customer_type)
      end
    end
  end
end
