module Items
  class ItemUpdaterService < BaseService
    attr_reader :id, :params

    def initialize(user:, params:)
      @user = user
      @id = params[:id]
      @params = params
    end

    def call
      item = Item.find(id)

      authorize!(ItemPolicy, :update?, item)

      item.update!(params)
      item
    end
  end
end
