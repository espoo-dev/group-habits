module Api
  module V1
    class ServiceOrdersController < Api::V1::ApiController
      def index
        service_orders = ServiceOrders::ServiceOrdersFinderService.new(user: current_user, params: index_params).call
        render json: ServiceOrderPresenter.payload_for_list(service_orders)
      end

      private

      def index_params
        params.permit(:name)
      end
    end
  end
end
