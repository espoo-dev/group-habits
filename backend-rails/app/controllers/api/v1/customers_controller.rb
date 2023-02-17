module Api
  module V1
    class CustomersController < Api::V1::ApiController
      def index
        customers = Customers::CustomersFinderService.new(user: current_user, params: index_params).call
        render json: CustomerPresenter.payload_for_list(customers)
      end

      def create
        customer = Customers::CustomerCreatorService.new(user: current_user, create_customer_params:).call
        render json: CustomerPresenter.payload_for_item(customer), status: :created
      end

      def update
        customer = Customers::CustomerUpdaterService.new(user: current_user, update_customer_params:).call
        render json: CustomerPresenter.payload_for_item(customer), status: :ok
      end

      def destroy
        Customers::CustomerDestroyerService.new(user: current_user, destroy_params:).call
        render json: {}, status: :no_content
      end

      private

      def index_params
        params.permit(:name, :customer_type)
      end

      def create_customer_params
        params.permit(:name, :document_number, :phone, :state_inscription, :customer_type)
      end

      def update_customer_params
        params.permit(:id, :name, :document_number, :phone, :state_inscription, :customer_type)
      end
    end
  end
end
